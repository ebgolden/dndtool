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
import com.ebgolden.persistence.operatorservice.module.LocalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCampaignOnNetworkTest {
    private Player player;
    @Mock
    private Operator mockOperator;
    private GetCampaignOnNetwork getCampaignOnNetwork;

    @BeforeEach
    public void setup() {
        player = Player
                .builder()
                .id("1")
                .build();
        Object api = GetCampaignOnNetwork.class;
        int port = 1;
        Injector injector = Guice.createInjector(Modules.override(new LocalNetworkOperatorModule(player,
                api,
                port))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(Operator.class).toInstance(mockOperator);
                    }
                }));
        getCampaignOnNetwork = injector.getInstance(GetCampaignOnNetwork.class);
    }

    @Test
    public void shouldReturnResponse() {
        OperatorResponseQuery operatorResponseQuery = OperatorResponseQuery
                .builder()
                .responseJson("{\"id\":\"123\"}")
                .build();
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        CampaignOnNetworkRequest campaignOnNetworkRequest = CampaignOnNetworkRequest
                .builder()
                .player(player)
                .build();
        CampaignOnNetworkResponse campaignOnNetworkResponse = getCampaignOnNetwork.getCampaignOnNetworkResponse(campaignOnNetworkRequest);
        Campaign campaign = campaignOnNetworkResponse.getCampaign();
        Assertions.assertNotNull(campaign, "Campaign null.");
    }
}