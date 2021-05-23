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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetResultDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetResultDetails getResultDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ResultDetailModule());
        getResultDetails = injector.getInstance(GetResultDetails.class);
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.EVERYONE);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.PLAYER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, false);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() != null)), "Result null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnResultWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String resultPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(resultPlayerId, Visibility.DUNGEON_MASTER);
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, resultPlayerId, true);
        Assertions.assertTrue(((resultDetailsResponse.getResult() != null) && (resultDetailsResponse.getResult().getId() == null)), "Result not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoResultWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(resultDetailsResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnNoResultWhenNullJson() {
        String playerId = "1";
        ResultDetailsResponse resultDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(resultDetailsResponse.getResult(), "Result not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String resultPlayerId, Visibility idVisibility) {
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

    private ResultDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnResultDetailsResponse(String responseJson, String playerId, String resultPlayerId, boolean isPlayer) {
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
        ResultDetailsRequest resultDetailsRequest = ResultDetailsRequest
                .builder()
                .result(Result
                        .builder()
                        .playerId(resultPlayerId)
                        .build())
                .player(player)
                .build();
        return getResultDetails.getResultDetailsResponse(resultDetailsRequest);
    }
}