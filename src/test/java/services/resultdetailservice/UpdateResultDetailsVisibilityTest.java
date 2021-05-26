package services.resultdetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.resultdetailservice.module.ResultDetailModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateResultDetailsVisibilityTest {
    @Mock
    DataOperator<UpdateResultDetailsVisibility> mockDataOperator;
    private UpdateResultDetailsVisibility updateResultDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ResultDetailModule());
        updateResultDetailsVisibility = injector.getInstance(UpdateResultDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ResultDetailsVisibilityResponse resultDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(resultDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId);
        ResultDetailsVisibilityResponse resultDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertNotNull(resultDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = "{}";
        ResultDetailsVisibilityResponse resultDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertNull(resultDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ResultDetailsVisibilityResponse resultDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(resultDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ResultDetailsVisibilityResponse resultDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(null, playerId, playerId, true);
        Assertions.assertNull(resultDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String resultPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"resultDetails\":");
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

    private ResultDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnResultDetailsVisibilityResponse(String responseJson, String playerId, String resultPlayerId, boolean isPlayer) {
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
        ResultDetailsVisibilityRequest resultDetailsVisibilityRequest = ResultDetailsVisibilityRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return updateResultDetailsVisibility.getResultDetailsVisibilityResponse(resultDetailsVisibilityRequest);
    }
}