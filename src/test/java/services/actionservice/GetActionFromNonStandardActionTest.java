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
public class GetActionFromNonStandardActionTest {
    @Mock
    DataOperator mockDataOperator;
    GetActionFromNonStandardAction getActionFromNonStandardAction;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ActionModule());
        getActionFromNonStandardAction = injector.getInstance(GetActionFromNonStandardAction.class);
    }

    @Test
    public void shouldReturnActionWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJson();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotEquals(null, actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnActionWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJson();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertNotEquals(null, actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnNoActionWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = "{}";
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenNullJson() {
        String playerId = "1";
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(null, playerId, playerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            actionJson = "{}";
        }
        return actionJson;
    }

    private ActionFromNonStandardActionResponse mockJsonResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        ActionFromNonStandardActionRequest actionFromNonStandardActionRequest = ActionFromNonStandardActionRequest
                .builder()
                .nonStandardAction(NonStandardAction
                        .builder()
                        .build())
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return getActionFromNonStandardAction.getActionFromNonStandardActionResponse(actionFromNonStandardActionRequest);
    }
}