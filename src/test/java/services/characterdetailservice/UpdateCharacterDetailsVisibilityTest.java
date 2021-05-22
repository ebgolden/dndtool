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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCharacterDetailsVisibilityTest {
    @Mock
    DataOperator mockDataOperator;
    private UpdateCharacterDetailsVisibility updateCharacterDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CharacterDetailModule());
        updateCharacterDetailsVisibility = injector.getInstance(UpdateCharacterDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(characterDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId);
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertNotNull(characterDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = "{}";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertNull(characterDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(characterDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(null, playerId, playerId, true);
        Assertions.assertNull(characterDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId) {
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
        return responseJson.toString();
    }

    private CharacterDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsVisibilityResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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
        CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest = CharacterDetailsVisibilityRequest
                .builder()
                .character(Character
                        .builder()
                        .playerId(characterPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return updateCharacterDetailsVisibility.getCharacterDetailsVisibilityResponse(characterDetailsVisibilityRequest);
    }
}