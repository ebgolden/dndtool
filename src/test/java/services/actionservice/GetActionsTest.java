package services.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import objects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.actionservice.module.ActionModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActionsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetActions getActions;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule(GetActions.class));
        getActions = injector.getInstance(GetActions.class);
    }

    @Test
    public void shouldReturnThreeActionsWhilePlayer() {
        String playerId = "1";
        int actionCount = 3;
        String responseJson = createMockResponseJson(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(responseJson, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnThreeActionsWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 3;
        String responseJson = createMockResponseJson(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnNoActionsWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 0;
        String responseJson = "{}";
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActions() {
        String playerId = "1";
        int actionCount = 0;
        String responseJson = createMockResponseJson(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(responseJson, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActionsWhenEmptyJson() {
        String playerId = "1";
        int actionCount = 0;
        String responseJson = "{}";
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(responseJson, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActionsWhenNullJson() {
        String playerId = "1";
        int actionCount = 0;
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(null, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    private String createMockResponseJson(int actionCount) {
        StringBuilder responseJson = new StringBuilder("[");
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            actionJson = "{}";
        }
        for (int currentActionIndex = 0; currentActionIndex < actionCount; ++currentActionIndex) {
            responseJson.append(actionJson);
            if (currentActionIndex < (actionCount - 1))
                responseJson.append(",");
        }
        responseJson.append("]");
        return responseJson.toString();
    }

    private ActionsResponse mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        ActionsRequest actionsRequest = ActionsRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return getActions.getActionsResponse(actionsRequest);
    }
}