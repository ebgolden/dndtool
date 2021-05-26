package services.characterdetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import objects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterdetailservice.module.CharacterDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCharacterDetailsTest {
    @Mock
    DataOperator<GetCharacterDetails> mockDataOperator;
    private GetCharacterDetails getCharacterDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterDetailModule());
        getCharacterDetails = injector.getInstance(GetCharacterDetails.class);
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.EVERYONE);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.PLAYER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, Visibility.DUNGEON_MASTER);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() == null)), "Character not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCharacterWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(characterDetailsResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnNoCharacterWhenNullJson() {
        String playerId = "1";
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(characterDetailsResponse.getCharacter(), "Character not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"characterDetails\":");
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

    private CharacterDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        CharacterDetailsRequest characterDetailsRequest = CharacterDetailsRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .player(player)
                .build();
        return getCharacterDetails.getCharacterDetailsResponse(characterDetailsRequest);
    }
}