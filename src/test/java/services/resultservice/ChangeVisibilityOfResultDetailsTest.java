package services.resultservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.resultservice.module.ResultModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfResultDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfResultDetails changeVisibilityOfResultDetails;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player player = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new ResultModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        ChangeVisibilityOfResultDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfResultDetails = injector.getInstance(ChangeVisibilityOfResultDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseJsonWithResultVisibilityOfId(playerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseJsonWithResultVisibilityOfId(resultPlayerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(dataOperatorResponseQuery, playerId, resultPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(dataOperatorResponseQuery, playerId, resultPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseJsonWithResultVisibilityOfId(String resultPlayerId) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"result\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson;
        String visibilityJson;
        String resultId = "1";
        try {
            resultJson = objectMapper.writeValueAsString(Result
                    .builder()
                    .id(resultId)
                    .playerId(resultPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            resultJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(resultJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private DataOperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private ChangeVisibilityOfResultDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String resultPlayerId, boolean isPlayer) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .id(playerId)
                    .build();
        else player = DungeonMaster
                .builder()
                .id(playerId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest = ChangeVisibilityOfResultDetailsRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfResultDetails.getChangeVisibilityOfResultDetailsResponse(changeVisibilityOfUpdatedResultRequest);
    }
}