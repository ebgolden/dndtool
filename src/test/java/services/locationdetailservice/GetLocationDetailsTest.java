package services.locationdetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.DataOperator;
import objects.DungeonMaster;
import objects.Location;
import objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.locationdetailservice.module.LocationDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLocationDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetLocationDetails getLocationDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new LocationDetailModule());
        getLocationDetails = injector.getInstance(GetLocationDetails.class);
    }

    @Test
    public void shouldReturnLocationWithIdWhilePlayer() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, true);
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(responseJson, true);
        Assertions.assertTrue(((locationDetailsResponse.getLocation() != null) && (locationDetailsResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDM() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, true);
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(responseJson, false);
        Assertions.assertTrue(((locationDetailsResponse.getLocation() != null) && (locationDetailsResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDMWhileVisibilityFalse() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, false);
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(responseJson, false);
        Assertions.assertTrue(((locationDetailsResponse.getLocation() != null) && (locationDetailsResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithoutIdWhilePlayerWhileVisibilityFalse() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, false);
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(responseJson, true);
        Assertions.assertTrue(((locationDetailsResponse.getLocation() != null) && (locationDetailsResponse.getLocation().getId() == null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoLocationWhenEmptyJson() {
        String responseJson = "{}";
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(responseJson, true);
        Assertions.assertNull(locationDetailsResponse.getLocation(), "Location not null.");
    }

    @Test
    public void shouldReturnNoLocationWhenNullJson() {
        LocationDetailsResponse locationDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(null, true);
        Assertions.assertNull(locationDetailsResponse.getLocation(), "Location not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String locationId, boolean idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"locationDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson;
        try {
            locationJson = objectMapper.writeValueAsString(Location
                    .builder()
                    .id(locationId)
                    .build());
        } catch (JsonProcessingException e) {
            locationJson = "{}";
        }
        responseJson
                .append(locationJson)
                .append(",\"visibility\":{\"id\":")
                .append(idVisibility)
                .append("}}");
        return responseJson.toString();
    }

    private LocationDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        LocationDetailsRequest locationDetailsRequest = LocationDetailsRequest
                .builder()
                .location(Location
                        .builder()
                        .build())
                .player(player)
                .build();
        return getLocationDetails.getLocationDetailsResponse(locationDetailsRequest);
    }
}