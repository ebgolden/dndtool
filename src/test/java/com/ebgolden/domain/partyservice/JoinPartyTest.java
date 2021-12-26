package com.ebgolden.domain.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
import com.ebgolden.common.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ebgolden.domain.partyservice.module.PartyModule;
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JoinPartyTest {
    @Mock
    Operator mockOperator;
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
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        joinParty = injector.getInstance(JoinParty.class);
    }

    @Test
    public void shouldReturnPartyWhilePlayer() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, playerId, true, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnPartyWhileDM() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, characterPlayerId, false, true);
        Assertions.assertNotNull(joinPartyResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileDifferentPlayer() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, characterPlayerId, true, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhilePlayerNotAccepted() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, playerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyPartyWhileDMNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithEmptyParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, characterPlayerId, false, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhileDifferentPlayerNotAccepted() {
        String playerId = "2";
        String characterPlayerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithPartyWithCharacterArrayInParty();
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, characterPlayerId, true, false);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        String playerId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        JoinPartyResponse joinPartyResponse = mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(operatorResponseQuery, playerId, playerId, false, true);
        Assertions.assertNull(joinPartyResponse.getParty(), "Party not null.");
    }

    private OperatorResponseQuery createMockResponseWithPartyWithCharacterArrayInParty() {
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

    private JoinPartyResponse mockJsonResponseAsPlayerOrDMAndReturnJoinPartyResponse(OperatorResponseQuery operatorResponseQuery, String playerId, String characterPlayerId, boolean isPlayer, boolean acceptedByParty) {
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