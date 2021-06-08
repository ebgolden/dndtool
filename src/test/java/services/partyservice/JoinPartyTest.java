package services.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import commonobjects.*;
import commonobjects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.partyservice.module.PartyModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JoinPartyTest {
    @Mock
    DataOperator mockDataOperator;
    private JoinParty joinParty;

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
        Injector injector = Guice.createInjector(new PartyModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        JoinParty.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        joinParty = injector.getInstance(JoinParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, playerId, true, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhilePlayerNotAccepted() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, playerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyPartyWhileDMNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayerNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(dataOperatorResponseQuery, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithPartyWithCharacterArrayInParty() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .characters(new Character[] {})
                    .build());
        } catch (JsonProcessingException e) {
            partyJson = "{}";
        }
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(partyJson)
                .build();
    }

    private DataOperatorResponseQuery createMockResponseWithPartyWithEmptyParty() {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            partyJson = "{}";
        }
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(partyJson)
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

    private JoinPartyResponse mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer, boolean acceptedByParty) {
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
        Character character = Character
                .builder()
                .id("0")
                .playerId(characterPlayerId)
                .build();
        Party party = Party
                .builder()
                .characters(new Character[] {character})
                .build();
        JoinPartyRequest joinPartyRequest = JoinPartyRequest
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .acceptedByParty(acceptedByParty)
                .build();
        return joinParty.getJoinPartyResponse(joinPartyRequest);
    }
}