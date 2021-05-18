package services.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import objects.Character;
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
        }, new PartyModule());
        leaveParty = injector.getInstance(LeaveParty.class);
    }

    @Test
    public void shouldReturnLeftPartyWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, true);
        Assertions.assertTrue(leavePartyResponse.leftParty, "Did not leave party.");
    }

    @Test
    public void shouldReturnLeftPartyWhileDM() {
        String playerId = "2";
        String responseJson = createMockResponseJsonWithEmptyParty();
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, false);
        Assertions.assertTrue(leavePartyResponse.leftParty, "Did not leave party.");
    }

    @Test
    public void shouldReturnNotLeftPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterId = "0";
        String responseJson = createMockResponseJsonWithCharacterInParty(characterId, playerId);
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, true);
        Assertions.assertFalse(leavePartyResponse.leftParty, "Left party.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        LeavePartyResponse leavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(responseJson, playerId, false);
        Assertions.assertFalse(leavePartyResponse.leftParty, "Left party.");
    }

    @Test
    public void shouldReturnEmptyJsonWhenNullJson() {
        String playerId = "1";
        LeavePartyResponse LeavePartyResponse = mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(null, playerId, false);
        Assertions.assertFalse(LeavePartyResponse.leftParty, "Left party.");
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

    private LeavePartyResponse mockJsonResponseAsPlayerOrDMAndReturnLeavePartyResponse(String responseJson, String playerId, boolean isPlayer) {
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
                .playerId(playerId)
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