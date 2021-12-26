package com.ebgolden.domain.itemservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.itemservice.module.ItemModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfItemDetailsTest {
    @Mock
    Operator mockOperator;
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
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeVisibilityOfItemDetails = injector.getInstance(ChangeVisibilityOfItemDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(playerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithItemWithVisibilityOfId(itemPlayerId);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(operatorResponseQuery, playerId, itemPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String itemPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(operatorResponseQuery, playerId, itemPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfItemDetailsResponse changeVisibilityOfUpdatedItemResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedItemResponse.getVisibilityMap(), "Visibility not null.");
    }

    private OperatorResponseQuery createMockResponseWithItemWithVisibilityOfId(String itemPlayerId) {
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

    private ChangeVisibilityOfItemDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfItemDetailsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String itemPlayerId, boolean isPlayer) {
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