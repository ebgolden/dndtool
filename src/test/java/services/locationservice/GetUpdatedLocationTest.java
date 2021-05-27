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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedLocationTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedLocation getUpdatedLocation;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new LocationModule(GetUpdatedLocation.class));
        getUpdatedLocation = injector.getInstance(GetUpdatedLocation.class);
    }

    @Test
    public void shouldReturnLocationWithIdWhilePlayer() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, Visibility.EVERYONE);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(responseJson, true);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDM() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, Visibility.EVERYONE);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(responseJson, false);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDMWhileVisibilityFalse() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, Visibility.DUNGEON_MASTER);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(responseJson, false);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithoutIdWhilePlayerWhileVisibilityFalse() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId, Visibility.DUNGEON_MASTER);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(responseJson, true);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() == null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoLocationWhenEmptyJson() {
        String responseJson = "{}";
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(responseJson, true);
        Assertions.assertNull(updatedLocationResponse.getLocation(), "Location not null.");
    }

    @Test
    public void shouldReturnNoLocationWhenNullJson() {
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(null, true);
        Assertions.assertNull(updatedLocationResponse.getLocation(), "Location not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String locationId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"location\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson;
        String visibilityJson;
        try {
            locationJson = objectMapper.writeValueAsString(Location
                    .builder()
                    .id(locationId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedLocationResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        UpdatedLocationRequest updatedLocationRequest = UpdatedLocationRequest
                .builder()
                .location(Location
                        .builder()
                        .build())
                .player(player)
                .build();
        return getUpdatedLocation.getUpdatedLocationResponse(updatedLocationRequest);
    }
}