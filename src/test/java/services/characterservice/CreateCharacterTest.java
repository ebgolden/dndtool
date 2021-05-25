package services.characterservice;

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
import services.characterservice.module.CharacterModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCharacterTest {
    @Mock
    DataOperator mockDataOperator;
    private CreateCharacter createCharacter;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterModule());
        createCharacter = injector.getInstance(CreateCharacter.class);
    }

    @Test
    public void shouldReturnCharacterWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(createCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnCharacterWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId);
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertNotNull(createCharacterResponse.getCharacter(), "Character null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = "{}";
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    @Test
    public void shouldReturnEmptyCharacterWhenNullJson() {
        String playerId = "1";
        CreateCharacterResponse createCharacterResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(null, playerId, playerId, true);
        Assertions.assertNull(createCharacterResponse.getCharacter(), "Character not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId) {
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
        return characterJson;
    }

    private CreateCharacterResponse mockJsonResponseAsPlayerOrDMAndReturnCreateCharacterResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        CreateCharacterRequest createCharacterRequest = CreateCharacterRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return createCharacter.getCreateCharacterResponse(createCharacterRequest);
    }
}