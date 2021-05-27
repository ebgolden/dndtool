package services.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import commonobjects.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.partyservice.module.PartyModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeavePartyTest {
    @Mock
    DataOperator mockDataOperator;
    private LeaveParty leaveParty;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new PartyModule(LeaveParty.class));
        leaveParty = injector.getInstance(LeaveParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, playerId, true);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, characterPlayerId, false);
        Assertions.assertNotNull(leavePartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String characterId = "0";
        String responseJson = createMockResponseJsonWithCharacterInParty(characterId, characterPlayerId);
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, characterPlayerId, true);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, playerId, false);
        Assertions.assertNull(leavePartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullJson() {
        String playerId = "1";
        LeavePartyResponse LeavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(null, playerId, playerId, false);
        Assertions.assertNull(LeavePartyResponse.getParty(), "Party not null.");
    }

    private String createMockResponseJsonWithCharacterInParty(String characterId, String playerId) {
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
        return partyJson;
    }

    private String createMockResponseJsonWithEmptyParty() {
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            partyJson = "{}";
        }
        return partyJson;
    }

    private LeavePartyResponse mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer) {
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