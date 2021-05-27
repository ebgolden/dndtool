package services.spellservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.spellservice.module.SpellModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedSpellTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedSpell getUpdatedSpell;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new SpellModule(GetUpdatedSpell.class));
        getUpdatedSpell = injector.getInstance(GetUpdatedSpell.class);
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoSpellWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    @Test
    public void shouldReturnNoSpellWhenNullJson() {
        String playerId = "1";
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(null, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String spellPlayerId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"spell\":");
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
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedSpellResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(String responseJson, String playerId, String spellPlayerId, boolean isPlayer) {
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
        UpdatedSpellRequest updatedSpellRequest = UpdatedSpellRequest
                .builder()
                .spell(Spell
                        .builder()
                        .playerId(spellPlayerId)
                        .build())
                .player(player)
                .build();
        return getUpdatedSpell.getUpdatedSpellResponse(updatedSpellRequest);
    }
}