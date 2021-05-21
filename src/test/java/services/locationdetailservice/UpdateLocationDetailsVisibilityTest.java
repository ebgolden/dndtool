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
    public void shouldReturnVisibilityJsonWithIdWhileDM() {
        String locationId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(locationId);
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsVisibilityResponse(responseJson, false);
        Assertions.assertNotEquals("{}", locationDetailsVisibilityResponse.getVisibilityJson(), "Visibility json empty.");
    }

    @Test
    public void shouldReturnEmptyVisibilityJsonWhilePlayer() {
        String responseJson = "{}";
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsVisibilityResponse(responseJson, true);
        Assertions.assertEquals("{}", locationDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenEmptyJson() {
        String responseJson = "{}";
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsVisibilityResponse(responseJson, false);
        Assertions.assertEquals("{}", locationDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenNullJson() {
        LocationDetailsVisibilityResponse locationDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsVisibilityResponse(null, false);
        Assertions.assertEquals("{}", locationDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String locationId) {
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
                .append(true)
                .append("}}");
        return responseJson.toString();
    }

    private LocationDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnLocationDetailsVisibilityResponse(String responseJson, boolean isPlayer) {
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
        LocationDetailsVisibilityRequest locationDetailsVisibilityRequest = LocationDetailsVisibilityRequest
                .builder()
                .location(Location
                        .builder()
                        .build())
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
        return updateLocationDetailsVisibility.getLocationDetailsVisibilityResponse(locationDetailsVisibilityRequest);
    }
}