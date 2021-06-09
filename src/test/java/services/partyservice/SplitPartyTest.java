package services.partyservice;

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
import services.partyservice.module.PartyModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SplitPartyTest {
    @Mock
    DataOperator mockDataOperator;
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
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        splitParty = injector.getInstance(SplitParty.class);
    }

    @Test
    public void shouldReturnThreeParties() {
        int partyCount = 3;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithParty(partyCount);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(dataOperatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoParties() {
        int partyCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithParty(partyCount);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(dataOperatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenEmptyResponse() {
        int partyCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(dataOperatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenNullResponse() {
        int partyCount = 0;
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        SplitPartyResponse splitPartyResponse = mockResponseAndReturnSplitPartyResponse(dataOperatorResponseQuery);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    private DataOperatorResponseQuery createMockResponseWithParty(int partyCount) {
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

    private SplitPartyResponse mockResponseAndReturnSplitPartyResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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