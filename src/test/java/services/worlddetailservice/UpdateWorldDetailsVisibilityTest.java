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
public class UpdateWorldDetailsVisibilityTest {
    @Mock
    DataOperator mockDataOperator;
    private UpdateWorldDetailsVisibility updateWorldDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new WorldDetailModule());
        updateWorldDetailsVisibility = injector.getInstance(UpdateWorldDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityJsonWithIdWhileDM() {
        String worldId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId);
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, false);
        Assertions.assertNotEquals("{}", worldDetailsVisibilityResponse.getVisibilityJson(), "Visibility json null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityJsonWhilePlayer() {
        String responseJson = "{}";
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, true);
        Assertions.assertEquals("{}", worldDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenEmptyJson() {
        String responseJson = "{}";
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(responseJson, false);
        Assertions.assertEquals("{}", worldDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenNullJson() {
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(null, false);
        Assertions.assertEquals("{}", worldDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String worldId) {
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
                .append(true)
                .append("}}");
        return responseJson.toString();
    }

    private WorldDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnWorldDetailsResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        String visibilityJson = "{\"id\":" + true + "}";
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        WorldDetailsVisibilityRequest worldDetailsVisibilityRequest = WorldDetailsVisibilityRequest
                .builder()
                .world(World
                        .builder()
                        .build())
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
        return updateWorldDetailsVisibility.getWorldDetailsVisibilityResponse(worldDetailsVisibilityRequest);
    }
}