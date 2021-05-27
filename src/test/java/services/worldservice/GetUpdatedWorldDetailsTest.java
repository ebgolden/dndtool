package services.worldservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.worldservice.module.WorldModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedWorldDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedWorldDetails getWorldDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new WorldModule(GetUpdatedWorldDetails.class));
        getWorldDetails = injector.getInstance(GetUpdatedWorldDetails.class);
    }

    @Test
    public void shouldReturnWorldWithIdWhilePlayer() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, Visibility.EVERYONE);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(responseJson, true);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDM() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, Visibility.EVERYONE);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(responseJson, false);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDMWhileVisibilityFalse() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, Visibility.DUNGEON_MASTER);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(responseJson, false);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() != null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithoutIdWhilePlayerWhileVisibilityFalse() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, Visibility.DUNGEON_MASTER);
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(responseJson, true);
        Assertions.assertTrue(((getUpdatedWorldResponse.getWorld() != null) && (getUpdatedWorldResponse.getWorld().getId() == null)), "World null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoWorldWhenEmptyJson() {
        String responseJson = "{}";
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(responseJson, true);
        Assertions.assertNull(getUpdatedWorldResponse.getWorld(), "World not null.");
    }

    @Test
    public void shouldReturnNoWorldWhenNullJson() {
        GetUpdatedWorldResponse getUpdatedWorldResponse = mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(null, true);
        Assertions.assertNull(getUpdatedWorldResponse.getWorld(), "World not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String worldId, Visibility idVisibility) {
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
        return responseJson.toString();
    }

    private GetUpdatedWorldResponse mockJsonResponseAsPlayerOrDMAndReturnGetUpdatedWorldResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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