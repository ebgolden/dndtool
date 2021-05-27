package services.locationservice;

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
import services.locationservice.module.LocationModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfLocationDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfLocationDetails changeVisibilityOfLocationDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new LocationModule(ChangeVisibilityOfLocationDetails.class));
        changeVisibilityOfLocationDetails = injector.getInstance(ChangeVisibilityOfLocationDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String locationId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId);
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String locationDungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(responseJson, dungeonMasterId, locationDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenNullJson() {
        String dungeonMasterId = "1";
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String locationId) {
        StringBuilder responseJson = new StringBuilder("{\"location\":");
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

    private ChangeVisibilityOfLocationDetailsResponse mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(String responseJson, String dungeonMasterId, String locationDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfLocationDetailsRequest changeVisibilityOfUpdatedLocationRequest = ChangeVisibilityOfLocationDetailsRequest
                .builder()
                .location(Location
                        .builder()
                        .dungeonMasterId(locationDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return changeVisibilityOfLocationDetails.getChangeVisibilityOfLocationDetailsResponse(changeVisibilityOfUpdatedLocationRequest);
    }
}