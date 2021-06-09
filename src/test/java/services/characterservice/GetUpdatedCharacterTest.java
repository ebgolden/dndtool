package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.Character;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterservice.module.CharacterModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedCharacterTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedCharacter = injector.getInstance(GetUpdatedCharacter.class);
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCharacterWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCharacterWhenEmptyResponse() {
        String playerId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnNoCharacterWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCharacterWithVisibilityOfId(String characterPlayerId, Visibility idVisibility) {
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

    private UpdatedCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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