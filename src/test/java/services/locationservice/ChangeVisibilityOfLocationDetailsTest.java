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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfLocationDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfLocationDetails changeVisibilityOfLocationDetails;

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
                        ChangeVisibilityOfLocationDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfLocationDetails = injector.getInstance(ChangeVisibilityOfLocationDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String locationId = "0";
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithLocationWithVisibilityOfId(locationId);
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String locationDungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, locationDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenEmptyResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyMapWhenNullResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfLocationDetailsResponse changeVisibilityOfUpdatedLocationResponse = mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedLocationResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithLocationWithVisibilityOfId(String locationId) {
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

    private ChangeVisibilityOfLocationDetailsResponse mockJsonResponseAndReturnChangeVisibilityOfLocationDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String dungeonMasterId, String locationDungeonMasterId) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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