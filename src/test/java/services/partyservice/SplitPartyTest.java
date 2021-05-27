package services.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.partyservice.module.PartyModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SplitPartyTest {
    @Mock
    DataOperator<SplitParty> mockDataOperator;
    private SplitParty splitParty;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new PartyModule());
        splitParty = injector.getInstance(SplitParty.class);
    }

    @Test
    public void shouldReturnThreeParties() {
        int partyCount = 3;
        String responseJson = createMockResponseJson(partyCount);
        SplitPartyResponse splitPartyResponse = mockJsonResponseAndReturnSplitPartyResponse(responseJson);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoParties() {
        int partyCount = 0;
        String responseJson = createMockResponseJson(partyCount);
        SplitPartyResponse splitPartyResponse = mockJsonResponseAndReturnSplitPartyResponse(responseJson);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenEmptyJson() {
        int partyCount = 0;
        String responseJson = "{}";
        SplitPartyResponse splitPartyResponse = mockJsonResponseAndReturnSplitPartyResponse(responseJson);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    @Test
    public void shouldReturnNoPartiesWhenNullJson() {
        int partyCount = 0;
        SplitPartyResponse splitPartyResponse = mockJsonResponseAndReturnSplitPartyResponse(null);
        Assertions.assertEquals(partyCount, splitPartyResponse.getSplitParties().length, "Wrong amount of parties.");
    }

    private String createMockResponseJson(int partyCount) {
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
        return responseJson.toString();
    }

    private SplitPartyResponse mockJsonResponseAndReturnSplitPartyResponse(String responseJson) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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