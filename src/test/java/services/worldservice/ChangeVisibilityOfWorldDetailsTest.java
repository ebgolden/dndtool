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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfWorldDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfWorldDetails changeVisibilityOfWorldDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new WorldModule(ChangeVisibilityOfWorldDetails.class));
        changeVisibilityOfWorldDetails = injector.getInstance(ChangeVisibilityOfWorldDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String worldId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(worldId);
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String worldDungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(responseJson, dungeonMasterId, worldDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String dungeonMasterId = "1";
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String worldId) {
        StringBuilder responseJson = new StringBuilder("{\"world\":");
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

    private ChangeVisibilityOfWorldDetailsResponse mockJsonResponseAndReturnGetUpdatedWorldResponse(String responseJson, String dungeonMasterId, String worldDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest = ChangeVisibilityOfWorldDetailsRequest
                .builder()
                .world(World
                        .builder()
                        .dungeonMasterId(worldDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return changeVisibilityOfWorldDetails.getChangeVisibilityOfWorldDetailsResponse(changeVisibilityOfGetUpdatedWorldRequest);
    }
}