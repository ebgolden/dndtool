package services.campaigndetailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.campaigndetailservice.module.CampaignDetailModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddPlayerToCampaignTest {
    @Mock
    DataOperator<AddPlayerToCampaign> mockDataOperator;
    private AddPlayerToCampaign addPlayerToCampaign;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignDetailModule());
        addPlayerToCampaign = injector.getInstance(AddPlayerToCampaign.class);
    }

    @Test
    public void shouldReturnCampaign() {
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJson();
        AddPlayerToCampaignResponse addPlayerToCampaignResponse = mockJsonResponseAndReturnAddPlayerToCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(addPlayerToCampaignResponse.getCampaign(), "Campaign null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        String responseJson = "{}";
        AddPlayerToCampaignResponse addPlayerToCampaignResponse = mockJsonResponseAndReturnAddPlayerToCampaignResponse(responseJson, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(addPlayerToCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        AddPlayerToCampaignResponse addPlayerToCampaignResponse = mockJsonResponseAndReturnAddPlayerToCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(addPlayerToCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenNullJson() {
        String dungeonMasterId = "1";
        AddPlayerToCampaignResponse addPlayerToCampaignResponse = mockJsonResponseAndReturnAddPlayerToCampaignResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(addPlayerToCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private String createMockResponseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        try {
            campaignJson = objectMapper.writeValueAsString(Campaign
                    .builder()
                    .build());
        } catch (JsonProcessingException e) {
            campaignJson = "{}";
        }
        return campaignJson;
    }

    private AddPlayerToCampaignResponse mockJsonResponseAndReturnAddPlayerToCampaignResponse(String responseJson, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        AddPlayerToCampaignRequest addPlayerToCampaignRequest = AddPlayerToCampaignRequest
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
        return addPlayerToCampaign.getAddPlayerToCampaignResponse(addPlayerToCampaignRequest);
    }
}