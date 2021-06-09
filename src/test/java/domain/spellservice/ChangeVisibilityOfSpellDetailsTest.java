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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfSpellDetailsTest {
    @Mock
    Operator mockOperator;
    private ChangeVisibilityOfSpellDetails changeVisibilityOfSpellDetails;

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
                        ChangeVisibilityOfSpellDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        changeVisibilityOfSpellDetails = injector.getInstance(ChangeVisibilityOfSpellDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(operatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(operatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    private OperatorResponseQuery createMockResponseWithSpellWithVisibilityOfId(String spellPlayerId) {
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

    private ChangeVisibilityOfSpellDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String spellPlayerId, boolean isPlayer) {
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
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfSpellDetailsRequest changeVisibilityOfUpdatedSpellRequest = ChangeVisibilityOfSpellDetailsRequest
                .builder()
                .spell(Spell
                        .builder()
                        .playerId(spellPlayerId)
                        .build())
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
        return changeVisibilityOfSpellDetails.getChangeVisibilityOfSpellDetailsResponse(changeVisibilityOfUpdatedSpellRequest);
    }
}