package domain.partyservice;

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
import domain.partyservice.module.PartyModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SplitPartyTest {
    @Mock
    Operator mockOperator;
    private SplitParty splitParty;

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
                        SplitParty.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        splitParty = injector.getInstance(SplitParty.class);
    }

    @Test
    public void shouldReturnThreeParties() {
        int partyCount = 3;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithParty(partyCount);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(operatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoParties() {
        int partyCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithParty(partyCount);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(operatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenEmptyResponse() {
        int partyCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(operatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenNullResponse() {
        int partyCount = 0;
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(operatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    private OperatorResponseQuery createMockResponseWithParty(int partyCount) {
        String queryId = "123";
        StringBuilder responseJson = new StringBuilder("[");
        ObjectMapper objectMapper = new ObjectMapper();
        String partyJson;
        try {
            partyJson = objectMapper.writeValueAsString(Party
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            partyJson = "{}";
        }
        for (int currentPartyIndex = 0; currentPartyIndex < partyCount; ++currentPartyIndex) {
            responseJson.append(partyJson);
            if (currentPartyIndex < (partyCount - 1))
                responseJson.append(",");
        }
        responseJson.append("]");
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

    private SplitPartyResponse mockResponseAndReturnSplitPartyResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        SplitPartyRequest splitPartyRequest = SplitPartyRequest
                .builder()
                .party(Party
                        .builder()
                        .build())
                .splitParties(new Party[] {})
                .dungeonMaster(DungeonMaster
                        .builder()
                        .build())
                .build();
        return splitParty.getSplitPartyResponse(splitPartyRequest);
    }
}