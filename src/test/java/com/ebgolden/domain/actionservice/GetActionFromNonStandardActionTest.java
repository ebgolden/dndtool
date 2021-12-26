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
public class GetActionFromNonStandardActionTest {
    @Mock
    Operator mockOperator;
    GetActionFromNonStandardAction getActionFromNonStandardAction;

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
                        GetActionFromNonStandardAction.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getActionFromNonStandardAction = injector.getInstance(GetActionFromNonStandardAction.class);
    }

    @Test
    public void shouldReturnActionWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, playerId, true, true);
        Assertions.assertNotNull(actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnActionWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithAction();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, characterPlayerId, false, true);
        Assertions.assertNotNull(actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnEmptyActionWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, characterPlayerId, true, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyActionWhilePlayerNotApproved() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, playerId, true, false);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyActionPartyWhileDMNotApproved() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, characterPlayerId, false, false);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyActionWhileDifferentPlayerNotApproved() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, characterPlayerId, true, false);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyActionWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, playerId, true, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnEmptyActionWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(operatorResponseQuery, playerId, playerId, true, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
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

    private ActionFromNonStandardActionResponse mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer, boolean approvedByDungeonMaster) {
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
                .approvedByDungeonMaster(approvedByDungeonMaster)
                .build();
        return getActionFromNonStandardAction.getActionFromNonStandardActionResponse(actionFromNonStandardActionRequest);
    }
}