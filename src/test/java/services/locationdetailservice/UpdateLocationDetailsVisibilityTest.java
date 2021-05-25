package services.locationdetailservice;

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
import services.locationdetailservice.module.LocationDetailModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateLocationDetailsVisibilityTest {
    @Mock
    DataOperator mockDataOperator;
    private UpdateLocationDetailsVisibility updateLocationDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new LocationDetailModule());
        updateLocationDetailsVisibility = injector.getInstance(UpdateLocationDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String locationId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId);
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAndReturnLocationDetailsVisibilityResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(locationDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String locationDungeonMasterId = "1";
        String responseJson = "{}";
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAndReturnLocationDetailsVisibilityResponse(responseJson, dungeonMasterId, locationDungeonMasterId);
        Assertions.assertNull(locationDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAndReturnLocationDetailsVisibilityResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(locationDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenNullJson() {
        String dungeonMasterId = "1";
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAndReturnLocationDetailsVisibilityResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(locationDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String locationId) {
        StringBuilder responseJson = new StringBuilder("{\"locationDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson;
        String visibilityJson;
        try {
            locationJson = objectMapper.writeValueAsString(Location
                    .builder()
                    .id(locationId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            locationJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(locationJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return responseJson.toString();
    }

    private LocationDetailsVisibilityResponse mockJsonResponseAndReturnLocationDetailsVisibilityResponse(String responseJson, String dungeonMasterId, String locationDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        LocationDetailsVisibilityRequest locationDetailsVisibilityRequest = LocationDetailsVisibilityRequest
                .builder()
                .location(Location
                        .builder()
                        .dungeonMasterId(locationDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return updateLocationDetailsVisibility.getLocationDetailsVisibilityResponse(locationDetailsVisibilityRequest);
    }
}