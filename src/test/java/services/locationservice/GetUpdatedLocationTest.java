package services.locationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.locationservice.module.LocationModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedLocationTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedLocation getUpdatedLocation;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player player = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new LocationModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedLocation.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedLocation = injector.getInstance(GetUpdatedLocation.class);
    }

    @Test
    public void shouldReturnLocationWithIdWhilePlayer() {
        String locationId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithLocationWithVisibilityOfId(locationId, Visibility.EVERYONE);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, true);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDM() {
        String locationId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithLocationWithVisibilityOfId(locationId, Visibility.EVERYONE);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, false);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithIdWhileDMWhileVisibilityFalse() {
        String locationId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithLocationWithVisibilityOfId(locationId, Visibility.DUNGEON_MASTER);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, false);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() != null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnLocationWithoutIdWhilePlayerWhileVisibilityFalse() {
        String locationId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithLocationWithVisibilityOfId(locationId, Visibility.DUNGEON_MASTER);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, true);
        Assertions.assertTrue(((updatedLocationResponse.getLocation() != null) && (updatedLocationResponse.getLocation().getId() == null)), "Location null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoLocationWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, true);
        Assertions.assertNull(updatedLocationResponse.getLocation(), "Location not null.");
    }

    @Test
    public void shouldReturnNoLocationWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedLocationResponse updatedLocationResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(dataOperatorResponseQuery, true);
        Assertions.assertNull(updatedLocationResponse.getLocation(), "Location not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithLocationWithVisibilityOfId(String locationId, Visibility idVisibility) {
        String queryId = "123";
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private DataOperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedLocationResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedLocationResponse(DataOperatorResponseQuery dataOperatorResponseQuery, boolean isPlayer) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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