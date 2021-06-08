package services.worldservice;

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
import services.worldservice.module.WorldModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfWorldDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfWorldDetails changeVisibilityOfWorldDetails;

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
        Injector injector = Guice.createInjector(new WorldModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        ChangeVisibilityOfWorldDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfWorldDetails = injector.getInstance(ChangeVisibilityOfWorldDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String worldId = "0";
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithWorldWithVisibilityOfId(worldId);
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String worldDungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(dataOperatorResponseQuery, dungeonMasterId, worldDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfWorldDetailsResponse changeVisibilityOfGetUpdatedWorldResponse = mockJsonResponseAndReturnGetUpdatedWorldResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfGetUpdatedWorldResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithWorldWithVisibilityOfId(String worldId) {
        String queryId = "123";
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

    private ChangeVisibilityOfWorldDetailsResponse mockJsonResponseAndReturnGetUpdatedWorldResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String dungeonMasterId, String worldDungeonMasterId) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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