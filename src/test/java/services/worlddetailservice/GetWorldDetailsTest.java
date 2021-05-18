package services.worlddetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.worlddetailservice.module.WorldDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetWorldDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetWorldDetails getWorldDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new WorldDetailModule());
        getWorldDetails = injector.getInstance(GetWorldDetails.class);
    }

    @Test
    public void shouldReturnWorldWithIdWhilePlayer() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, true);
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, true);
        Assertions.assertTrue(((worldDetailsResponse.getWorld() != null) && (worldDetailsResponse.getWorld().getId() != null)), "Wrong amount of worlds and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDM() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, true);
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, false);
        Assertions.assertTrue(((worldDetailsResponse.getWorld() != null) && (worldDetailsResponse.getWorld().getId() != null)), "Wrong amount of worlds and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithIdWhileDMWhileVisibilityFalse() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, false);
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, false);
        Assertions.assertTrue(((worldDetailsResponse.getWorld() != null) && (worldDetailsResponse.getWorld().getId() != null)), "Wrong amount of worlds and/or wrong visibility.");
    }

    @Test
    public void shouldReturnWorldWithoutIdWhilePlayerWhileVisibilityFalse() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId, false);
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, true);
        Assertions.assertTrue(((worldDetailsResponse.getWorld() != null) && (worldDetailsResponse.getWorld().getId() == null)), "Wrong amount of worlds and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoWorldWhenEmptyJson() {
        String responseJson = "{}";
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, true);
        Assertions.assertNull(worldDetailsResponse.getWorld(), "Wrong amount of worlds.");
    }

    @Test
    public void shouldReturnNoWorldWhenNullJson() {
        WorldDetailsResponse worldDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(null, true);
        Assertions.assertNull(worldDetailsResponse.getWorld(), "Wrong amount of worlds.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String worldId, boolean idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"worldDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson;
        try {
            worldJson = objectMapper.writeValueAsString(World
                    .builder()
                    .id(worldId)
                    .build());
        } catch (JsonProcessingException e) {
            worldJson = "{}";
        }
        responseJson
                .append(worldJson)
                .append(",\"visibility\":{\"id\":")
                .append(idVisibility)
                .append("}}");
        return responseJson.toString();
    }

    private WorldDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        WorldDetailsRequest worldDetailsRequest = WorldDetailsRequest
                .builder()
                .world(World
                        .builder()
                        .build())
                .player(player)
                .build();
        return getWorldDetails.getWorldDetailsResponse(worldDetailsRequest);
    }
}