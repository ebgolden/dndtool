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
import com.ebgolden.persistence.operatorservice.module.WideNetworkOperatorModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCampaignNetworkAddressTest {
    private Player player;
    @Mock
    private Operator mockOperator;
    private GetCampaignNetworkAddress getCampaignNetworkAddress;

    @BeforeEach
    public void setup() {
        player = Player
                .builder()
                .id("1")
                .build();
        Object api = GetCampaignNetworkAddress.class;
        Injector injector = Guice.createInjector(Modules.override(new WideNetworkOperatorModule(player,
                api))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(Operator.class).toInstance(mockOperator);
                    }
                }));
        getCampaignNetworkAddress = injector.getInstance(GetCampaignNetworkAddress.class);
    }

    @Test
    public void shouldReturnResponse() {
        when(mockOperator.findAndReturnIPAddress()).thenReturn("0.0.0.0");
        CampaignNetworkAddressRequest campaignNetworkAddressRequest = CampaignNetworkAddressRequest
                .builder()
                .player(player)
                .build();
        CampaignNetworkAddressResponse campaignNetworkAddressResponse = getCampaignNetworkAddress.getCampaignNetworkAddressResponse(campaignNetworkAddressRequest);
        String ipAddress = campaignNetworkAddressResponse.getIpAddress();
        Assertions.assertNotNull(ipAddress, "IP address null.");
    }
}