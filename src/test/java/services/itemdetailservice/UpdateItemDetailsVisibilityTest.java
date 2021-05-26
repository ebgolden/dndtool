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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateItemDetailsVisibilityTest {
    @Mock
    DataOperator<UpdateItemDetailsVisibility> mockDataOperator;
    private UpdateItemDetailsVisibility updateItemDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ItemDetailModule());
        updateItemDetailsVisibility = injector.getInstance(UpdateItemDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ItemDetailsVisibilityResponse itemDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(itemDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId);
        ItemDetailsVisibilityResponse itemDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertNotNull(itemDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = "{}";
        ItemDetailsVisibilityResponse itemDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertNull(itemDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ItemDetailsVisibilityResponse itemDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(itemDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ItemDetailsVisibilityResponse itemDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(null, playerId, playerId, true);
        Assertions.assertNull(itemDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String itemPlayerId) {
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
        return responseJson.toString();
    }

    private ItemDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnItemDetailsVisibilityResponse(String responseJson, String playerId, String itemPlayerId, boolean isPlayer) {
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
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ItemDetailsVisibilityRequest itemDetailsVisibilityRequest = ItemDetailsVisibilityRequest
                .builder()
                .item(Item
                        .builder()
                        .playerId(itemPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return updateItemDetailsVisibility.getItemDetailsVisibilityResponse(itemDetailsVisibilityRequest);
    }
}