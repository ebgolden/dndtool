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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCampaignDetailsVisibilityTest {
    @Mock
    DataOperator mockDataOperator;
    private UpdateCampaignDetailsVisibility updateCampaignDetailsVisibility;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignDetailModule());
        updateCampaignDetailsVisibility = injector.getInstance(UpdateCampaignDetailsVisibility.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String campaignId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId);
        CampaignDetailsVisibilityResponse campaignDetailsVisibilityResponse = mockJsonResponseAndReturnCampaignDetailsVisibilityResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(campaignDetailsVisibilityResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        String responseJson = "{}";
        CampaignDetailsVisibilityResponse campaignDetailsVisibilityResponse = mockJsonResponseAndReturnCampaignDetailsVisibilityResponse(responseJson, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(campaignDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        CampaignDetailsVisibilityResponse campaignDetailsVisibilityResponse = mockJsonResponseAndReturnCampaignDetailsVisibilityResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(campaignDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String dungeonMasterId = "1";
        CampaignDetailsVisibilityResponse campaignDetailsVisibilityResponse = mockJsonResponseAndReturnCampaignDetailsVisibilityResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(campaignDetailsVisibilityResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignId) {
        StringBuilder responseJson = new StringBuilder("{\"campaignDetails\":");
        ObjectMapper objectMapper = new ObjectMapper();
        String campaignJson;
        String visibilityJson;
        try {
            campaignJson = objectMapper.writeValueAsString(Campaign
                    .builder()
                    .id(campaignId)
                    .build());
            visibilityJson = objectMapper.writeValueAsString(Visibility.PLAYER);
        } catch (JsonProcessingException e) {
            campaignJson = "{}";
            visibilityJson = "{}";
        }
        responseJson
                .append(campaignJson)
                .append(",\"visibility\":{\"id\":")
                .append(visibilityJson)
                .append("}}");
        return responseJson.toString();
    }

    private CampaignDetailsVisibilityResponse mockJsonResponseAndReturnCampaignDetailsVisibilityResponse(String responseJson, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        CampaignDetailsVisibilityRequest campaignDetailsVisibilityRequest = CampaignDetailsVisibilityRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .dungeonMasterId(campaignDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return updateCampaignDetailsVisibility.getCampaignDetailsVisibilityResponse(campaignDetailsVisibilityRequest);
    }
}