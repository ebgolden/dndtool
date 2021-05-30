package services.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import commonobjects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.actionservice.module.ActionModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActionsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetActions getActions;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player senderPlayer = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new ActionModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        GetActions.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getActions = injector.getInstance(GetActions.class);
    }

    @Test
    public void shouldReturnThreeActionsWhilePlayer() {
        String playerId = "1";
        int actionCount = 3;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnThreeActionsWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 3;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnNoActionsWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActions() {
        String playerId = "1";
        int actionCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActionsWhenEmptyResponse() {
        String playerId = "1";
        int actionCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnNoActionsWhenNullResponse() {
        String playerId = "1";
        int actionCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    private DataOperatorResponseQuery createMockResponseWithAction(int actionCount) {
        String queryId = "123";
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

    private ActionsResponse mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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