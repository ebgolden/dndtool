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
public class LeavePartyTest {
    @Mock
    DataOperator mockDataOperator;
    private LeaveParty leaveParty;

    @BeforeEach
    public void setup() {
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player senderPlayer = Player
                .builder()
                .id("1")
                .build();
        Injector injector = Guice.createInjector(new PartyModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        senderPlayer,
                        LeaveParty.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        leaveParty = injector.getInstance(LeaveParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(dataOperatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String characterId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithPartyWithCharacterInParty(characterId, characterPlayerId);
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(dataOperatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(dataOperatorResponseQuery, playerId, playerId, false);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        String playerId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        LeavePartyResponse LeavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(dataOperatorResponseQuery, playerId, playerId, false);
        Assertions.assertNull(LeavePartyResponse.getParty(), "Party not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithPartyWithCharacterInParty(String characterId, String playerId) {
        String queryId = "123";
        Character character = Character
                .builder()
                .id(characterId)
                .playerId(playerId)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .characters(new Character[] {character})
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

    private LeavePartyResponse mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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
        LeavePartyRequest leavePartyRequest = LeavePartyRequest
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .build();
        return leaveParty.getLeavePartyResponse(leavePartyRequest);
    }
}