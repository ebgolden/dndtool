package services.spellservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.spellservice.module.SpellModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedSpellTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedSpell getUpdatedSpell;

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
        Injector injector = Guice.createInjector(new SpellModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        GetUpdatedSpell.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedSpell = injector.getInstance(GetUpdatedSpell.class);
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoSpellWhenEmptyResponse() {
        String playerId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    @Test
    public void shouldReturnNoSpellWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithSpellWithVisibilityOfId(String spellPlayerId, Visibility idVisibility) {
        String queryId = "123";
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

    private UpdatedSpellResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String spellPlayerId, boolean isPlayer) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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