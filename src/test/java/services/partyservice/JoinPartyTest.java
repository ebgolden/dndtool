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
public class JoinPartyTest {
    @Mock
    DataOperator mockDataOperator;
    private JoinParty joinParty;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new PartyModule(JoinParty.class));
        joinParty = injector.getInstance(JoinParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, playerId, true, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, characterPlayerId, false, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, characterPlayerId, true, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhilePlayerNotAccepted() {
        String playerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, playerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyPartyWhileDMNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, characterPlayerId, false, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileOtherPlayerNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        String responseJson = createMockResponseJsonWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, characterPlayerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyJson() {
        String playerId = "1";
        String responseJson = "{}";
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(responseJson, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullJson() {
        String playerId = "1";
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(null, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    private String createMockResponseJsonWithCharacterArrayInParty() {
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

    private JoinPartyResponse mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(String responseJson, String playerId, String characterPlayerId, boolean isPlayer, boolean acceptedByParty) {
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