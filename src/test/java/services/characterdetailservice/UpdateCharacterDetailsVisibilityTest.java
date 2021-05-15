package services.characterdetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.CharacterObject;
import objects.DataOperator;
import objects.DungeonMaster;
import objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.characterdetailservice.module.CharacterDetailModule;
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
    public void shouldReturnVisibilityJsonWithIdWhilePlayer() {
        String characterPlayerId = "0";
        String playerId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId);
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, true);
        Assertions.assertNotEquals("{}", characterDetailsVisibilityResponse.getVisibilityJson(), "Visibility json null.");
    }

    @Test
    public void shouldReturnVisibilityJsonWithIdWhileDM() {
        String characterPlayerId = "0";
        String playerId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(characterPlayerId);
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, false);
        Assertions.assertNotEquals("{}", characterDetailsVisibilityResponse.getVisibilityJson(), "Visibility json null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityJsonWhileDifferentPlayer() {
        String playerId = "0";
        String responseJson = "{}";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, true);
        Assertions.assertEquals("{}", characterDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(responseJson, playerId, true);
        Assertions.assertEquals("{}", characterDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenNullJson() {
        String playerId = "0";
        CharacterDetailsVisibilityResponse characterDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(null, playerId, true);
        Assertions.assertEquals("{}", characterDetailsVisibilityResponse.getVisibilityJson(), "Visibility json not empty.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String characterPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"characterDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson;
        String characterId = "1";
        try {
            characterJson = objectMapper.writeValueAsString(CharacterObject
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
                .append(true)
                .append("}}");
        return responseJson.toString();
    }

    private CharacterDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnCharacterDetailsResponse(String responseJson, String playerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        String visibilityJson = "{\"id\":" + true + "}";
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
        CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest = CharacterDetailsVisibilityRequest
                .builder()
                .character(CharacterObject
                        .builder()
                        .build())
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
        return updateCharacterDetailsVisibility.getCharacterDetailsVisibilityResponse(characterDetailsVisibilityRequest);
    }
}