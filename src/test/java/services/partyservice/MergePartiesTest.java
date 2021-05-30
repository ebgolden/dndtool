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
public class MergePartiesTest {
    @Mock
    DataOperator mockDataOperator;
    private MergeParties mergeParties;

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
                        MergeParties.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        mergeParties = injector.getInstance(MergeParties.class);
    }

    @Test
    public void shouldReturnParty() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithParty();
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(dataOperatorResponseQuery);
        Assertions.assertNotNull(mergePartiesResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(dataOperatorResponseQuery);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(dataOperatorResponseQuery);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithParty() {
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

    private MergePartiesResponse mockResponseAndReturnMergePartiesResponse(DataOperatorResponseQuery dataOperatorResponseQuery) {
        when(mockDataOperator.getResponseJson(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        MergePartiesRequest mergePartiesRequest = MergePartiesRequest
                .builder()
                .parties(new Party[] {})
                .dungeonMaster(DungeonMaster
                        .builder()
                        .build())
                .build();
        return mergeParties.getMergePartiesResponse(mergePartiesRequest);
    }
}