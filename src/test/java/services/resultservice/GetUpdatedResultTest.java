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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedResultTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedResult getUpdatedResult;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ResultModule(GetUpdatedResult.class));
        getUpdatedResult = injector.getInstance(GetUpdatedResult.class);
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((updatedResultResponse.getResult() != null) && (updatedResultResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoResultWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(updatedResultResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnNoResultWhenNullJson() {
        String playerId = "1";
        UpdatedResultResponse updatedResultResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(null, playerId, playerId, true);
        Assertions.assertNull(updatedResultResponse.getResult(), "Result not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String resultPlayerId, Visibility idVisibility) {
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
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedResultResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedResultResponse(String responseJson, String playerId, String resultPlayerId, boolean isPlayer) {
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
        UpdatedResultRequest updatedResultRequest = UpdatedResultRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedResult.getUpdatedResultResponse(updatedResultRequest);
    }
}