package domain.itemservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.itemservice.module.ItemModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedItemTest {
    @Mock
    Operator mockOperator;
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
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedItem = injector.getInstance(GetUpdatedItem.class);
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.EVERYONE);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.PLAYER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() != null)), "Item null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnItemWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertTrue(((updatedItemResponse.getItem() != null) && (updatedItemResponse.getItem().getId() == null)), "Item not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnEmptyItemWhenEmptyResponse() {
        String playerId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    @Test
    public void shouldReturnEmptyItemWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedItemResponse updatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedItemResponse.getItem(), "Item not null.");
    }

    private OperatorResponseQuery createMockResponseWithItemWithVisibilityOfId(String itemPlayerId, Visibility idVisibility) {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedItemResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedItemResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String itemPlayerId, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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