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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetSpellDetailsTest {
    @Mock
    DataOperator<GetSpellDetails> mockDataOperator;
    private GetSpellDetails getSpellDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new SpellDetailModule());
        getSpellDetails = injector.getInstance(GetSpellDetails.class);
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.EVERYONE);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.PLAYER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, false);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, spellPlayerId, true);
        Assertions.assertTrue(((spellDetailsResponse.getSpell() != null) && (spellDetailsResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoSpellWhenEmptyJson() {
        String playerId = "0";
        String responseJson = "{}";
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(responseJson, playerId, playerId, true);
        Assertions.assertNull(spellDetailsResponse.getSpell(), "Spell not null.");
    }

    @Test
    public void shouldReturnNoSpellWhenNullJson() {
        String playerId = "1";
        SpellDetailsResponse spellDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(null, playerId, playerId, true);
        Assertions.assertNull(spellDetailsResponse.getSpell(), "Spell not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String spellPlayerId, Visibility idVisibility) {
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

    private SpellDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnSpellDetailsResponse(String responseJson, String playerId, String spellPlayerId, boolean isPlayer) {
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
        SpellDetailsRequest spellDetailsRequest = SpellDetailsRequest
                .builder()
                .spell(Spell
                        .builder()
                        .playerId(spellPlayerId)
                        .build())
                .player(player)
                .build();
        return getSpellDetails.getSpellDetailsResponse(spellDetailsRequest);
    }
}