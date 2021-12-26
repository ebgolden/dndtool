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
public class TakeActionTest {
    @Mock
    Operator mockOperator;
    private TakeAction takeAction;

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
                        TakeAction.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        takeAction = injector.getInstance(TakeAction.class);
    }

    @Test
    public void shouldReturnResultWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnResultWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnEmptyResultWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyResultWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnEmptyResultWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
    }

    private OperatorResponseQuery createMockResponseWithAction() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            actionJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(actionJson)
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

    private TakeActionResponse mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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
        TakeActionRequest takeActionRequest = TakeActionRequest
                .builder()
                .action(Action
                        .builder()
                        .build())
                .dice(new Die[] {})
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return takeAction.getTakeActionResponse(takeActionRequest);
    }
}