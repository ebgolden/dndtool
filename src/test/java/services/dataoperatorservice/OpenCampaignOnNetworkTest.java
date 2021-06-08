package services.dataoperatorservice;

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
import services.dataoperatorservice.module.LocalNetworkOperatorModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpenCampaignOnNetworkTest {
    private DungeonMaster dungeonMaster;
    @Mock
    private DataOperator mockDataOperator;
    private OpenCampaignOnNetwork openCampaignOnNetwork;

    @BeforeEach
    public void setup() {
        dungeonMaster = DungeonMaster
                .builder()
                .id("1")
                .build();
        Object api = OpenCampaignOnNetwork.class;
        Injector injector = Guice.createInjector(Modules.override(new LocalNetworkOperatorModule(dungeonMaster,
                api))
                .with(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(DataOperator.class).toInstance(mockDataOperator);
                    }
                }));
        openCampaignOnNetwork = injector.getInstance(OpenCampaignOnNetwork.class);
    }

    @Test
    public void shouldReturnResponse() {
        when(mockDataOperator.openAndReturnUnusedPort()).thenReturn(1);
        OpenCampaignOnNetworkRequest openCampaignOnNetworkRequest = OpenCampaignOnNetworkRequest
                .builder()
                .dungeonMaster(dungeonMaster)
                .build();
        OpenCampaignOnNetworkResponse openCampaignOnNetworkResponse = openCampaignOnNetwork.getOpenCampaignOnNetworkResponse(openCampaignOnNetworkRequest);
        int port = openCampaignOnNetworkResponse.getPort();
        Assertions.assertNotEquals(0, port, "Campaign null.");
    }
}