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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfItemDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfItemDetails changeVisibilityOfItemDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new ItemModule(ChangeVisibilityOfItemDetails.class));
        changeVisibilityOfItemDetails = injector.getInstance(ChangeVisibilityOfItemDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(itemPlayerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(responseJson, playerId, itemPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(responseJson, playerId, itemPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String itemPlayerId) {
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
        return responseJson.toString();
    }

    private ChangeVisibilityOfItemDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(String responseJson, String playerId, String itemPlayerId, boolean isPlayer) {
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