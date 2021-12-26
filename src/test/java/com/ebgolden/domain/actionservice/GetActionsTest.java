package com.ebgolden.domain.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
import com.ebgolden.common.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.actionservice.module.ActionModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActionsTest {
    @Mock
    Operator mockOperator;
    private GetActions getActions;

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
        Injector injector = Guice.createInjector(new ActionModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetActions.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getActions = injector.getInstance(GetActions.class);
    }

    @Test
    public void shouldReturnThreeActionsWhilePlayer() {
        String playerId = "1";
        int actionCount = 3;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnThreeActionsWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 3;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Wrong amount of actions.");
    }

    @Test
    public void shouldReturnEmptyActionsWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        int actionCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnEmptyActions() {
        String playerId = "1";
        int actionCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction(actionCount);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnEmptyActionsWhenEmptyResponse() {
        String playerId = "1";
        int actionCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    @Test
    public void shouldReturnEmptyActionsWhenNullResponse() {
        String playerId = "1";
        int actionCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ActionsResponse actionsResponse = mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertEquals(actionCount, actionsResponse.getActions().length, "Actions not empty.");
    }

    private OperatorResponseQuery createMockResponseWithAction(int actionCount) {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private ActionsResponse mockJsonResponseAsPlayerOrDMAndReturnActionsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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