package services.itemservice;

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
import services.itemservice.module.ItemModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedItemTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedItem getUpdatedItem;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ItemModule(GetUpdatedItem.class));
        getUpdatedItem = injector.getInstance(GetUpdatedItem.class);
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoItemWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    @Test
    public void shouldReturnNoItemWhenNullJson() {
        String playerId = "1";
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(null, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String itemPlayerId, Visibility idVisibility) {
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
        return responseJson.toString();
    }

    private UpdatedItemResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(String responseJson, String playerId, String itemPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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