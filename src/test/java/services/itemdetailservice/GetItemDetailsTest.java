package services.itemdetailservice;

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
import services.itemdetailservice.module.ItemDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetItemDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private GetItemDetails getItemDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ItemDetailModule());
        getItemDetails = injector.getInstance(GetItemDetails.class);
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertTrue(((itemDetailsResponse.getItem() != null) && (itemDetailsResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoItemWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(itemDetailsResponse.getItem(), "Item not null.");
    }

    @Test
    public void shouldReturnNoItemWhenNullJson() {
        String playerId = "1";
        ItemDetailsResponse itemDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(itemDetailsResponse.getItem(), "Item not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String itemPlayerId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"itemDetails\":");
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

    private ItemDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnItemDetailsResponse(String responseJson, String playerId, String itemPlayerId, boolean isPlayer) {
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
        ItemDetailsRequest itemDetailsRequest = ItemDetailsRequest
                .builder()
                .item(Item
                        .builder()
                        .playerId(itemPlayerId)
                        .build())
                .player(player)
                .build();
        return getItemDetails.getItemDetailsResponse(itemDetailsRequest);
    }
}