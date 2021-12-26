package com.ebgolden.persistence.operatorservice;

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
import com.ebgolden.persistence.operatorservice.module.GlobalNetworkOperatorModule;
import com.ebgolden.domain.partyservice.JoinParty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendResponseQueryTest {
    private String queryId;
    @Mock
    private Operator mockOperator;
    private SendResponseQuery sendResponseQuery;

    @BeforeEach
    public void setup() {
        queryId = "1";
        Campaign campaign = Campaign
                .builder()
                .id("1")
                .build();
        Player player = Player
                .builder()
                .id("1")
                .build();
        Object api = JoinParty.class;
        Injector injector = Guice.createInjector(Modules.override(new GlobalNetworkOperatorModule(campaign,
                player,
                api))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(Operator.class).toInstance(mockOperator);
                    }
                }));
        sendResponseQuery = injector.getInstance(SendResponseQuery.class);
    }

    @Test
    public void shouldReturnResponse() {
        OperatorResponseQuery operatorResponseQuery = OperatorResponseQuery
                .builder()
                .responseJson("{}")
                .build();
        when(mockOperator.getOperatorResponseQuery(any(OperatorResponseQuery.class))).thenReturn(operatorResponseQuery);
        String responseJson = "{\"party\":{},\"character\":{}}";
        ResponseQueryRequest responseQueryRequest = ResponseQueryRequest
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
        ResponseQueryResponse responseQueryResponse = sendResponseQuery.getResponseQueryResponse(responseQueryRequest);
        Assertions.assertNotNull(responseQueryResponse.getResponseJson(), "Response json null.");
    }
}