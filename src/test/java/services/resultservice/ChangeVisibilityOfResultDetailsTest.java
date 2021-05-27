package services.resultservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.resultservice.module.ResultModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfResultDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfResultDetails changeVisibilityOfResultDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ResultModule(ChangeVisibilityOfResultDetails.class));
        changeVisibilityOfResultDetails = injector.getInstance(ChangeVisibilityOfResultDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId);
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ChangeVisibilityOfResultDetailsResponse changeVisibilityOfUpdatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedResultResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String resultPlayerId) {
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
        return responseJson.toString();
    }

    private ChangeVisibilityOfResultDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfResultDetailsResponse(String responseJson, String playerId, String resultPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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