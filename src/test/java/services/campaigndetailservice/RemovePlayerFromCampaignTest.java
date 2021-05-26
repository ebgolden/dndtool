package services.campaigndetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.Campaign;
import objects.DataOperator;
import objects.DungeonMaster;
import objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.campaigndetailservice.module.CampaignDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemovePlayerFromCampaignTest {
    @Mock
    DataOperator<RemovePlayerFromCampaign> mockDataOperator;
    private RemovePlayerFromCampaign removePlayerFromCampaign;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignDetailModule());
        removePlayerFromCampaign = injector.getInstance(RemovePlayerFromCampaign.class);
    }

    @Test
    public void shouldReturnCampaign() {
        String campaignId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId);
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        String responseJson = "{}";
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(responseJson, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenNullJson() {
        String dungeonMasterId = "1";
        RemovePlayerFromCampaignResponse removePlayerFromCampaignResponse = mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(removePlayerFromCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignId) {
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
        return campaignJson;
    }

    private RemovePlayerFromCampaignResponse mockJsonResponseAndReturnRemovePlayerFromCampaignResponse(String responseJson, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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