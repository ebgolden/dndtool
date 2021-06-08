package services.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
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
public class GetUpdatedActionTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedAction getUpdatedAction;

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
                        GetUpdatedAction.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedAction = injector.getInstance(GetUpdatedAction.class);
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.EVERYONE);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.PLAYER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, false);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() != null)), "Action null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnActionWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithActionWithVisibilityOfId(actionPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, actionPlayerId, true);
        Assertions.assertTrue(((updatedActionResponse.getAction() != null) && (updatedActionResponse.getAction().getId() == null)), "Action not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoActionWhenEmptyResponse() {
        String playerId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedActionResponse.getAction(), "Action not null.");
    }

    @Test
    public void shouldReturnNoActionWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedActionResponse updatedActionResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedActionResponse.getAction(), "Action not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithActionWithVisibilityOfId(String actionPlayerId, Visibility idVisibility) {
        String queryId = "123";
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

    private UpdatedActionResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedActionResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String actionPlayerId, boolean isPlayer) {
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