package domain.campaignservice;

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
import domain.campaignservice.module.CampaignModule;
import persistence.operatorservice.module.GlobalNetworkOperatorModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCampaignTest {
    @Mock
    Operator mockOperator;
    private CreateCampaign createCampaign;

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
        Injector injector = Guice.createInjector(new CampaignModule(),
                Modules.override(new GlobalNetworkOperatorModule(campaign,
                        player,
                        CreateCampaign.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Operator.class).toInstance(mockOperator);
                            }
                        }));
        createCampaign = injector.getInstance(CreateCampaign.class);
    }

    @Test
    public void shouldReturnCampaign() {
        String dungeonMasterId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponseWithCampaign(dungeonMasterId);
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAndReturnCreateCampaignResponse(operatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(createCampaignResponse.getCampaign(), "Campaign null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAndReturnCreateCampaignResponse(operatorResponseQuery, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenEmptyResponse() {
        String dungeonMasterId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse("{}");
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAndReturnCreateCampaignResponse(operatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenNullResponse() {
        String dungeonMasterId = "1";
        OperatorResponseQuery operatorResponseQuery = createMockResponse(null);
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAndReturnCreateCampaignResponse(operatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private OperatorResponseQuery createMockResponseWithCampaign(String campaignDungeonMasterId) {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        try {
            campaignJson = objectMapper.writeValueAsString(Campaign
                    .builder()
                    .dungeonMasterId(campaignDungeonMasterId)
                    .build());
        } catch (JsonProcessingException e) {
            campaignJson = "{}";
        }
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(campaignJson)
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

    private CreateCampaignResponse mockJsonResponseAndReturnCreateCampaignResponse(OperatorResponseQuery operatorResponseQuery, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockOperator.getOperatorResponseQuery(any(OperatorRequestQuery.class))).thenReturn(operatorResponseQuery);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        CreateCampaignRequest createCampaignRequest = CreateCampaignRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .dungeonMasterId(campaignDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return createCampaign.getCreateCampaignResponse(createCampaignRequest);
    }
}