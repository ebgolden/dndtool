package services.spelldetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.spelldetailservice.module.SpellDetailModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateSpellDetailsVisibilityTest {
    @Mock
    DataOperator mockDataOperator;
    private UpdateSpellDetailsVisibility updateSpellDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new SpellDetailModule());
        updateSpellDetailsVisibility = injector.getInstance(UpdateSpellDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId);
        SpellDetailsVisibilityResponse spellDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(spellDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId);
        SpellDetailsVisibilityResponse spellDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertNotNull(spellDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = "{}";
        SpellDetailsVisibilityResponse spellDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertNull(spellDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        SpellDetailsVisibilityResponse spellDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(spellDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String playerId = "1";
        SpellDetailsVisibilityResponse spellDetailsVisibilityResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(null, playerId, playerId, true);
        Assertions.assertNull(spellDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String spellPlayerId) {
        StringBuilder responseJson = new StringBuilder("{\"spellDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String spellJson;
        String visibilityJson;
        String spellId = "1";
        try {
            spellJson = objectMapper.writeValueAsString(Spell
                    .builder()
                    .id(spellId)
                    .playerId(spellPlayerId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            spellJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(spellJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return responseJson.toString();
    }

    private SpellDetailsVisibilityResponse mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsVisibilityResponse(String responseJson, String playerId, String spellPlayerId, boolean isPlayer) {
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
        SpellDetailsVisibilityRequest spellDetailsVisibilityRequest = SpellDetailsVisibilityRequest
                .builder()
                .spell(Spell
                        .builder()
                        .playerId(spellPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return updateSpellDetailsVisibility.getSpellDetailsVisibilityResponse(spellDetailsVisibilityRequest);
    }
}