package com.ebgolden.domain.partyservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.ebgolden.common.*;
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
public class MergePartiesTest {
    @Mock
    Operator mockOperator;
    private MergeParties mergeParties;

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
                        MergeParties.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        mergeParties = injector.getInstance(MergeParties.class);
    }

    @Test
    public void shouldReturnParty() {
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithParty();
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(operatorResponseQuery);
        Assertions.assertNotNull(mergePartiesResponse.getParty(), "Party null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenEmptyResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(operatorResponseQuery);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    @Test
    public void shouldReturnEmptyPartyWhenNullResponse() {
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        MergePartiesResponse mergePartiesResponse = mockResponseAndReturnMergePartiesResponse(operatorResponseQuery);
        Assertions.assertNull(mergePartiesResponse.getParty(), "Party not null.");
    }

    private OperatorResponseQuery createMockResponseWithParty() {
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

    private MergePartiesResponse mockResponseAndReturnMergePartiesResponse(OperatorResponseQuery operatorResponseQuery) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
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