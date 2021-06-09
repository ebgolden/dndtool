package domain.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import common.*;
import common.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.partyservice.module.PartyModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeavePartyTest {
    @Mock
    Operator mockOperator;
    private LeaveParty leaveParty;

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
                        LeaveParty.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        leaveParty = injector.getInstance(LeaveParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(operatorResponseQuery, playerId, playerId, true);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(operatorResponseQuery, playerId, characterPlayerId, false);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String characterId = "0";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithCharacterInParty(characterId, characterPlayerId);
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(operatorResponseQuery, playerId, characterPlayerId, true);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(operatorResponseQuery, playerId, playerId, false);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        LeavePartyResponse LeavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(operatorResponseQuery, playerId, playerId, false);
        Assertions.assertNull(LeavePartyResponse.getParty(), "Party not null.");
    }

    private OperatorResponseQuery createMockResponseWithPartyWithCharacterInParty(String characterId, String playerId) {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(partyJson)
                .build();
    }

    private OperatorResponseQuery createMockResponseWithPartyWithEmptyParty() {
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
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(partyJson)
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

    private LeavePartyResponse mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer) {
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