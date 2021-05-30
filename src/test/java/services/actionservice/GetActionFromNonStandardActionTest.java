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
public class GetActionFromNonStandardActionTest {
    @Mock
    DataOperator mockDataOperator;
    GetActionFromNonStandardAction getActionFromNonStandardAction;

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
                        GetActionFromNonStandardAction.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getActionFromNonStandardAction = injector.getInstance(GetActionFromNonStandardAction.class);
    }

    @Test
    public void shouldReturnActionWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnActionWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction();
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(actionFromNonStandardActionResponse.getAction(), "Action null.");
    }

    @Test
    public void shouldReturnNoActionWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ActionFromNonStandardActionResponse actionFromNonStandardActionResponse = mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(actionFromNonStandardActionResponse.getAction(), "Action not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithAction() {
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(actionJson)
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

    private ActionFromNonStandardActionResponse mockResponseAsPlayerOrDMAndReturnActionFromNonStandardActionResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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