package domain.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.Character;
import common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.characterservice.module.CharacterModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedCharacterTest {
    @Mock
    Operator mockOperator;
    private GetUpdatedCharacter getUpdatedCharacter;

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
                        GetUpdatedCharacter.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedCharacter = injector.getInstance(GetUpdatedCharacter.class);
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCharacterWhenEmptyResponse() {
        String playerId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnNoCharacterWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    private OperatorResponseQuery createMockResponseWithCharacterWithVisibilityOfId(String characterPlayerId, Visibility idVisibility) {
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
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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
        UpdatedCharacterRequest updatedCharacterRequest = UpdatedCharacterRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedCharacter.getUpdatedCharacterResponse(updatedCharacterRequest);
    }
}