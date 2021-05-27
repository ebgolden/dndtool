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
import java.util.HashMap;
import java.util.Map;
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
        }, new WorldDetailModule(UpdateWorldDetailsVisibility.class));
        updateWorldDetailsVisibility = injector.getInstance(UpdateWorldDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String worldId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId);
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAndReturnWorldDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(worldDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String worldDungeonMasterId = "1";
        String responseJson = "{}";
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAndReturnWorldDetailsResponse(responseJson, dungeonMasterId, worldDungeonMasterId);
        Assertions.assertNull(worldDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAndReturnWorldDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(worldDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String dungeonMasterId = "1";
        WorldDetailsVisibilityResponse worldDetailsVisibilityResponse = mockJsonResponseAndReturnWorldDetailsResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(worldDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String worldId) {
        StringBuilder responseJson = new StringBuilder("{\"worldDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson;
        String visibilityJson;
        try {
            worldJson = objectMapper.writeValueAsString(World
                    .builder()
                    .id(worldId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
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

    private WorldDetailsVisibilityResponse mockJsonResponseAndReturnWorldDetailsResponse(String responseJson, String dungeonMasterId, String worldDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        WorldDetailsVisibilityRequest worldDetailsVisibilityRequest = WorldDetailsVisibilityRequest
                .builder()
                .world(World
                        .builder()
                        .dungeonMasterId(worldDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return updateWorldDetailsVisibility.getWorldDetailsVisibilityResponse(worldDetailsVisibilityRequest);
    }
}