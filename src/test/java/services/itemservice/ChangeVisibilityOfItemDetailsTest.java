package services.itemservice;

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
import services.itemservice.module.ItemModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfItemDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfItemDetails changeVisibilityOfItemDetails;

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
        Injector injector = Guice.createInjector(new ItemModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        ChangeVisibilityOfItemDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfItemDetails = injector.getInstance(ChangeVisibilityOfItemDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(dataOperatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(dataOperatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithItemWithVisibilityOfId(String itemPlayerId) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"item\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson;
        String visibilityJson;
        String itemId = "1";
        try {
            itemJson = objectMapper.writeValueAsString(Item
                    .builder()
                    .id(itemId)
                    .playerId(itemPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            itemJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(itemJson)
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

    private ChangeVisibilityOfItemDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String itemPlayerId, boolean isPlayer) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .id(playerId)
                    .build();
        else player = DungeonMaster
                .builder()
                .id(playerId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfItemDetailsRequest changeVisibilityOfUpdatedItemRequest = ChangeVisibilityOfItemDetailsRequest
                .builder()
                .item(Item
                        .builder()
                        .playerId(itemPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfItemDetails.getChangeVisibilityOfItemDetailsResponse(changeVisibilityOfUpdatedItemRequest);
    }
}