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
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeVisibilityOfCampaignDetailsTest {
    @Mock
    DataOperator mockDataOperator;
    private ChangeVisibilityOfCampaignDetails changeVisibilityOfCampaignDetails;

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
                        ChangeVisibilityOfCampaignDetails.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        changeVisibilityOfCampaignDetails = injector.getInstance(ChangeVisibilityOfCampaignDetails.class);
    }

    @Test
    public void shouldReturnVisibilityMapWithId() {
        String campaignId = "0";
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaignWithVisibilityOfId(campaignId);
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNotNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhileDifferentDM() {
        String dungeonMasterId = "2";
        String campaignDungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, campaignDungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenEmptyResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    @Test
    public void shouldReturnEmptyVisibilityMapWhenNullResponse() {
        String dungeonMasterId = "1";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        ChangeVisibilityOfCampaignDetailsResponse changeVisibilityOfUpdatedCampaignResponse = mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(dataOperatorResponseQuery, dungeonMasterId, dungeonMasterId);
        Assertions.assertNull(changeVisibilityOfUpdatedCampaignResponse.getVisibilityMap(), "Visibility not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCampaignWithVisibilityOfId(String campaignId) {
        String queryId = "123";
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
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson.toString())
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

    private ChangeVisibilityOfCampaignDetailsResponse mockJsonResponseAndReturnChangeVisibilityOfCampaignDetailsResponse(DataOperatorResponseQuery dataOperatorResponseQuery, String dungeonMasterId, String campaignDungeonMasterId) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
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