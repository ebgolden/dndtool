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
    DataOperator mockDataOperator;
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
    public void shouldReturnCharacterWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, true);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, true);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, true);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhilePlayerWhileVisibilityFalse() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, false);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithIdWhileDMWhileVisibilityFalse() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, false);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() != null)), "Character null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCharacterWithoutIdWhileDifferentPlayerWhileVisibilityFalse() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId, false);
        CharacterDetailsResponse characterDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertTrue(((characterDetailsResponse.getCharacter() != null) && (characterDetailsResponse.getCharacter().getId() == null)), "Character null and/or wrong visibility.");
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

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId, boolean idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"characterDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        String characterId = "1";
        try {
            characterJson = objectMapper.writeValueAsString(Character
                    .builder()
                    .id(characterId)
                    .playerId(characterPlayerId)
                    .build());
        } catch (JsonProcessingException e) {
            characterJson = "{}";
        }
        responseJson
                .append(characterJson)
                .append(",\"visibility\":{\"id\":")
                .append(idVisibility)
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