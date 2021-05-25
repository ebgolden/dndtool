package services.campaignservice;

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
import services.campaignservice.module.CampaignModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCampaignTest {
    @Mock
    DataOperator mockDataOperator;
    private CreateCampaign createCampaign;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignModule());
        createCampaign = injector.getInstance(CreateCampaign.class);
    }

    @Test
    public void shouldReturnCampaign() {
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(dungeonMasterId);
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(createCampaignResponse.getCampaign(), "Campaign null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        String responseJson = "{}";
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCampaignResponse(responseJson, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCampaignResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnEmptyCampaignWhenNullJson() {
        String dungeonMasterId = "1";
        CreateCampaignResponse createCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnCreateCampaignResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(createCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignDungeonMasterId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        String campaignId = "1";
        try {
            campaignJson = objectMapper.writeValueAsString(Campaign
                    .builder()
                    .id(campaignId)
                    .dungeonMasterId(campaignDungeonMasterId)
                    .build());
        } catch (JsonProcessingException e) {
            campaignJson = "{}";
        }
        return campaignJson;
    }

    private CreateCampaignResponse mockJsonResponseAsPlayerOrDMAndReturnCreateCampaignResponse(String responseJson, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        CreateCampaignRequest createCampaignRequest = CreateCampaignRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .dungeonMasterId(campaignDungeonMasterId)
                        .build())
                .dungeonMaster(dungeonMaster)
                .build();
        return createCampaign.getCreateCampaignResponse(createCampaignRequest);
    }
}