package services.actiondetailservice;

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
import services.actiondetailservice.module.ActionDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActionDetailsTest {
    @Mock
    DataOperator<GetActionDetails> mockDataOperator;
    private GetActionDetails getActionDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionDetailModule());
        getActionDetails = injector.getInstance(GetActionDetails.class);
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((actionDetailsResponse.getAction() != null) && (actionDetailsResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoActionWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(actionDetailsResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenNullJson() {
        String playerId = "1";
        ActionDetailsResponse actionDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(actionDetailsResponse.getAction(), "Action not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String actionPlayerId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"actionDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        String visibilityJson;
        String actionId = "1";
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .id(actionId)
                    .playerId(actionPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
        } catch (JsonProcessingException e) {
            actionJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(actionJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return responseJson.toString();
    }

    private ActionDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnActionDetailsResponse(String responseJson, String playerId, String actionPlayerId, boolean isPlayer) {
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
        ActionDetailsRequest actionDetailsRequest = ActionDetailsRequest
                .builder()
                .action(Action
                        .builder()
                        .playerId(actionPlayerId)
                        .build())
                .player(player)
                .build();
        return getActionDetails.getActionDetailsResponse(actionDetailsRequest);
    }
}