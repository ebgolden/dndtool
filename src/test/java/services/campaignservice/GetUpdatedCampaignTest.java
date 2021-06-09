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
public class GetUpdatedCampaignTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedCampaign getUpdatedCampaign;

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
                        GetUpdatedCampaign.class))
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(DataOperator.class).toInstance(mockDataOperator);
                            }
                        }));
        getUpdatedCampaign = injector.getInstance(GetUpdatedCampaign.class);
    }

    @Test
    public void shouldReturnCampaignWithIdWhilePlayer() {
        String campaignId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaignWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, true);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDM() {
        String campaignId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaignWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, false);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDMWhileVisibilityFalse() {
        String campaignId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaignWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, false);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithoutIdWhilePlayerWhileVisibilityFalse() {
        String campaignId = "0";
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponseWithCampaignWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, true);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() == null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCampaignWhenEmptyResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse("{}");
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, true);
        Assertions.assertNull(updatedCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnNoCampaignWhenNullResponse() {
        DataOperatorResponseQuery dataOperatorResponseQuery = createMockResponse(null);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(dataOperatorResponseQuery, true);
        Assertions.assertNull(updatedCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private DataOperatorResponseQuery createMockResponseWithCampaignWithVisibilityOfId(String campaignId, Visibility idVisibility) {
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
            visibilityJson = objectMapper.writeValueAsString(idVisibility);
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

    private UpdatedCampaignResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(DataOperatorResponseQuery dataOperatorResponseQuery, boolean isPlayer) {
        when(mockDataOperator.getDataOperatorResponseQuery(any(DataOperatorRequestQuery.class))).thenReturn(dataOperatorResponseQuery);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        UpdatedCampaignRequest updatedCampaignRequest = UpdatedCampaignRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .build())
                .player(player)
                .build();
        return getUpdatedCampaign.getUpdatedCampaignResponse(updatedCampaignRequest);
    }
}