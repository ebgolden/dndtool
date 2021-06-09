package services.campaignservice;

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
import services.campaignservice.module.CampaignModule;
import services.dataoperatorservice.module.GlobalNetworkOperatorModule;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemovePlayerFromCampaignTest {
    @Mock
    DataOperator mockDataOperator;
    private RemovePlayerFromCampaign removePlayerFromCampaign;

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
                        RemovePlayerFromCampaign.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        removePlayerFromCampaign = injector.getInstance(RemovePlayerFromCampaign.class);
    }

    @Test
    public void shouldReturnCampaign() {
        String campaignId = "0";
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaign(campaignId);
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(dataOperatorResponseQuery, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenEmptyResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenNullResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCampaign(String campaignId) {
        String queryId = "123";
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        try {
            campaignJson = objectMapper.writeValueAsString(Campaign
                    .builder()
                    .id(campaignId)
                    .build());
        } catch (JsonProcessingException e) {
            campaignJson = "{}";
        }
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(campaignJson)
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

    private RemovePlayerFromCampaignResponse mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest = RemovePlayerFromCampaignRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .dungeonMasterId(campaignDungeonMasterId)
                        .build())
                .player(Player
                        .builder()
                        .build())
                .dungeonMaster(dungeonMaster)
                .build();
        return removePlayerFromCampaign.getRemovePlayerFromCampaignResponse(removePlayerFromCampaignRequest);
    }
}