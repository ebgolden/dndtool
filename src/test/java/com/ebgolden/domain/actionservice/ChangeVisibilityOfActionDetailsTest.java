package domain.actionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.actionservice.module.ActionModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfActionDetailsTest {
    @Mock
    private Operator mockOperator;
    private ChangeVisibilityOfActionDetails changeVisibilityOfActionDetails;

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
                        ChangeVisibilityOfActionDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeVisibilityOfActionDetails = injector.getInstance(ChangeVisibilityOfActionDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithVisibilityOfId(playerId);
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String actionPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithVisibilityOfId(actionPlayerId);
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(operatorResponseQuery, playerId, actionPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String actionPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(operatorResponseQuery, playerId, actionPlayerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfActionDetailsResponse changeVisibilityOfActionDetailsResponse = mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfActionDetailsResponse.getVisibilityMap(), "Visibility not null.");
    }

    private OperatorResponseQuery createMockResponseWithVisibilityOfId(String actionPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"action\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        String visibilityJson;
        String actionId = "1";
        String queryId = "123";
        try {
            actionJson = objectMapper.writeValueAsString(Action
                    .builder()
                    .id(actionId)
                    .playerId(actionPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            actionJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(actionJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
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

    private ChangeVisibilityOfActionDetailsResponse mockResponseAsPlayerOrDMAndReturnChangeVisibilityOfActionDetailsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String actionPlayerId, boolean isPlayer) {
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
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest = ChangeVisibilityOfActionDetailsRequest
                .builder()
                .action(Action
                        .builder()
                        .playerId(actionPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfActionDetails.getChangeVisibilityOfActionDetailsResponse(changeVisibilityOfActionDetailsRequest);
    }
}