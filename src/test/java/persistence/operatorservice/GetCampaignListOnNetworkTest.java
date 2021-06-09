package persistence.operatorservice;

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
import persistence.operatorservice.module.LocalNetworkOperatorModule;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCampaignListOnNetworkTest {
    private Player player;
    @Mock
    private Operator mockOperator;
    private GetCampaignListOnNetwork getCampaignListOnNetwork;

    @BeforeEach
    public void setup() {
        player = Player
                .builder()
                .id("1")
                .build();
        Object api = GetCampaignListOnNetwork.class;
        Injector injector = Guice.createInjector(Modules.override(new LocalNetworkOperatorModule(player,
                api))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(Operator.class).toInstance(mockOperator);
                    }
                }));
        getCampaignListOnNetwork = injector.getInstance(GetCampaignListOnNetwork.class);
    }

    @Test
    public void shouldReturnResponse() {
        when(mockOperator.getOpenPorts()).thenReturn(new int[] {});
        CampaignListOnNetworkRequest campaignListOnNetworkRequest = CampaignListOnNetworkRequest
                .builder()
                .player(player)
                .build();
        CampaignListOnNetworkResponse campaignListOnNetworkResponse = getCampaignListOnNetwork.getCampaignListOnNetworkResponse(campaignListOnNetworkRequest);
        Map<Integer, Campaign> portCampaignMap = campaignListOnNetworkResponse.getPortCampaignMap();
        Assertions.assertNotNull(portCampaignMap, "Port Campaign Map null.");
    }
}