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
public class TakeActionTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        takeAction = injector.getInstance(TakeAction.class);
    }

    @Test
    public void shouldReturnResultWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnResultWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithAction();
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(takeActionResponse.getResult(), "Action null.");
    }

    @Test
    public void shouldReturnNoResultWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Action not null.");
    }

    @Test
    public void shouldReturnNoResultWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
    }

    @Test
    public void shouldReturnNoResultWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        TakeActionResponse takeActionResponse = mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(takeActionResponse.getResult(), "Result not null.");
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

    private TakeActionResponse mockJsonResponseAsPlayerOrDMAndReturnTakeActionResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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