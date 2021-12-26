package domain.spellservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.spellservice.module.SpellModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedSpellTest {
    @Mock
    Operator mockOperator;
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
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        getUpdatedSpell = injector.getInstance(GetUpdatedSpell.class);
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityEveryone() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDifferentPlayerWhileVisibilityEveryone() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.EVERYONE);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhilePlayerWhileVisibilityPlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.PLAYER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhilePlayerWhileVisibilityDM() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithIdWhileDMWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() != null)), "Spell null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnSpellWithoutIdWhileDifferentPlayerWhileVisibilityDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId, Visibility.DUNGEON_MASTER);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertTrue(((updatedSpellResponse.getSpell() != null) && (updatedSpellResponse.getSpell().getId() == null)), "Spell not null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnEmptySpellWhenEmptyResponse() {
        String playerId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    @Test
    public void shouldReturnEmptySpellWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        UpdatedSpellResponse updatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(updatedSpellResponse.getSpell(), "Spell not null.");
    }

    private OperatorResponseQuery createMockResponseWithSpellWithVisibilityOfId(String spellPlayerId, Visibility idVisibility) {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
                .build();
    }

    private OperatorResponseQuery createMockResponse(String responseJson) {
        String queryId = "123";
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private UpdatedSpellResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedSpellResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String spellPlayerId, boolean isPlayer) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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