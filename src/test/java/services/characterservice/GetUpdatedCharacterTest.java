package services.characterservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.Character;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterservice.module.CharacterModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedCharacter getUpdatedCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule(GetUpdatedCharacter.class));
        getUpdatedCharacter = injector.getInstance(GetUpdatedCharacter.class);
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((updatedCharacterResponse.getCharacter() != null) && (updatedCharacterResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCharacterWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnNoCharacterWhenNullJson() {
        String playerId = "1";
        UpdatedCharacterResponse updatedCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(null, playerId, playerId, true);
        Assertions.assertNull(updatedCharacterResponse.getCharacter(), "Character not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId, Visibility idVisibility) {
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
        return responseJson.toString();
    }

    private UpdatedCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedCharacterResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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