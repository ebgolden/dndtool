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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedItemTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedItem getUpdatedItem;

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
                        GetUpdatedItem.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedItem = injector.getInstance(GetUpdatedItem.class);
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoItemWhenEmptyResponse() {
        String playerId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    @Test
    public void shouldReturnNoItemWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithItemWithVisibilityOfId(String itemPlayerId, Visibility idVisibility) {
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
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedItemResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String itemPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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
        UpdatedItemRequest updatedItemRequest = UpdatedItemRequest
                .builder()
                .item(Item
                        .builder()
                        .playerId(itemPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedItem.getUpdatedItemResponse(updatedItemRequest);
    }
}