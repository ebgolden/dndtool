package services.campaignservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.Campaign;
import commonobjects.DataOperator;
import commonobjects.DungeonMaster;
import commonobjects.Visibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.campaignservice.module.CampaignModule;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfCampaignDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfCampaignDetails changeVisibilityOfCampaignDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignModule(ChangeVisibilityOfCampaignDetails.class));
        changeVisibilityOfCampaignDetails = injector.getInstance(ChangeVisibilityOfCampaignDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String campaignId = "0";
        String dungeonMasterId = "1";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId);
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(responseJson, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyJson() {
        String dungeonMasterId = "1";
        String responseJson = "{}";
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(responseJson, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullJson() {
        String dungeonMasterId = "1";
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(null, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignId) {
        StringBuilder responseJson = new StringBuilder("{\"campaign\":");
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

    private ChangeVisibilityOfCampaignDetailsResponse mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(String responseJson, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        DungeonMaster dungeonMaster = DungeonMaster
                .builder()
                .id(dungeonMasterId)
                .build();
        Map<String, Visibility> visibilityMap = new HashMap<>();
        visibilityMap.put("id", Visibility.EVERYONE);
        ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest = ChangeVisibilityOfCampaignDetailsRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .dungeonMasterId(campaignDungeonMasterId)
                        .build())
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
        return changeVisibilityOfCampaignDetails.getChangeVisibilityOfCampaignDetailsResponse(changeVisibilityOfUpdatedCampaignRequest);
    }
}