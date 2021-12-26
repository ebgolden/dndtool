package com.ebgolden.domain.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.Character;
import com.ebgolden.common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.characterservice.module.CharacterModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfCharacterDetailsTest {
    @Mock
    Operator mockOperator;
    private ChangeVisibilityOfCharacterDetails changeVisibilityOfCharacterDetails;

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
        Injector injector = Guice.createInjector(new CharacterModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        ChangeVisibilityOfCharacterDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeVisibilityOfCharacterDetails = injector.getInstance(ChangeVisibilityOfCharacterDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfCharacterDetailsResponse changeVisibilityOfUpdatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedCharacterResponse.getVisibilityMap(), "Visibility not null.");
    }

    private OperatorResponseQuery createMockResponseWithCharacterWithVisibilityOfId(String characterPlayerId) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("{\"character\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        String visibilityJson;
        String characterId = "1";
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .id(characterId)
                    .playerId(characterPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            characterJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(characterJson)
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

    private ChangeVisibilityOfCharacterDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfCharacterDetailsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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
        ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest = ChangeVisibilityOfCharacterDetailsRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfCharacterDetails.getChangeVisibilityOfCharacterDetailsResponse(changeVisibilityOfUpdatedCharacterRequest);
    }
}