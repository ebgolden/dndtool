package domain.worldservice;

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
import domain.worldservice.module.WorldModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedWorldDetailsTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedWorldDetails getWorldDetails;

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
        Injector injector = Guice.createInjector(new WorldModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedWorldDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getWorldDetails = injector.getInstance(GetUpdatedWorldDetails.class);
    }

    @Test
    public void shouldReturnWorldWithIdWhilePlayer() {
        String worldId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithWorldWithVisibilityOfId(worldId, Visibility.EVERYONE);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, true);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDM() {
        String worldId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithWorldWithVisibilityOfId(worldId, Visibility.EVERYONE);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, false);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDMWhileVisibilityFalse() {
        String worldId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithWorldWithVisibilityOfId(worldId, Visibility.DUNGEON_MASTER);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, false);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithoutIdWhilePlayerWhileVisibilityFalse() {
        String worldId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithWorldWithVisibilityOfId(worldId, Visibility.DUNGEON_MASTER);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, true);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() == null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoWorldWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, true);
        Assertions.assertNull(getUpdatedWorldResponse.getWorld(), "World not null.");
    }

    @Test
    public void shouldReturnNoWorldWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(operatorResponseQuery, true);
        Assertions.assertNull(getUpdatedWorldResponse.getWorld(), "World not null.");
    }

    private OperatorResponseQuery createMockResponseWithWorldWithVisibilityOfId(String worldId, Visibility idVisibility) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"world\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson;
        String visibilityJson;
        try {
            worldJson = objectMapper.writeValueAsString(World
                    .builder()
                    .id(worldId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
        } catch (JsonProcessingException e) {
            worldJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(worldJson)
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

    private GetUpdatedWorldResponse mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(OperatorResponseQuery operatorResponseQuery, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        GetUpdatedWorldRequest getUpdatedWorldRequest = GetUpdatedWorldRequest
                .builder()
                .world(World
                        .builder()
                        .build())
                .player(player)
                .build();
        return getWorldDetails.getGetUpdatedWorldResponse(getUpdatedWorldRequest);
    }
}