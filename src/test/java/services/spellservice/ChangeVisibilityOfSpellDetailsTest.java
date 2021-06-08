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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfSpellDetailsTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfSpellDetails = injector.getInstance(ChangeVisibilityOfSpellDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(playerId);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnVisibilityMapWithIdWhileDM() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithSpellWithVisibilityOfId(spellPlayerId);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(dataOperatorResponseQuery, playerId, spellPlayerId, false);
        Assertions.assertNotNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentPlayer() {
        String playerId = "2";
        String spellPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(dataOperatorResponseQuery, playerId, spellPlayerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfSpellDetailsResponse changeVisibilityOfUpdatedSpellResponse = mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNull(changeVisibilityOfUpdatedSpellResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithSpellWithVisibilityOfId(String spellPlayerId) {
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

    private ChangeVisibilityOfSpellDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnChangeVisibilityOfSpellDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String spellPlayerId, boolean isPlayer) {
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