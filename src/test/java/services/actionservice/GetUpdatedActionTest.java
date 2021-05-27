package services.actionservice;

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
import services.actionservice.module.ActionModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedActionTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedAction getUpdatedAction;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule(GetUpdatedAction.class));
        getUpdatedAction = injector.getInstance(GetUpdatedAction.class);
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoActionWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(updatedActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenNullJson() {
        String playerId = "1";
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(null, playerId, playerId, true);
        Assertions.assertNull(updatedActionResponse.getAction(), "Action not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String actionPlayerId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"action\":");
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

    private UpdatedActionResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(String responseJson, String playerId, String actionPlayerId, boolean isPlayer) {
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
        UpdatedActionRequest updatedActionRequest = UpdatedActionRequest
                .builder()
                .action(Action
                        .builder()
                        .playerId(actionPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedAction.getUpdatedActionResponse(updatedActionRequest);
    }
}