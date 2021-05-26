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
public class GetCampaignDetailsTest {
    @Mock
    DataOperator<GetCampaignDetails> mockDataOperator;
    private GetCampaignDetails getCampaignDetails;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignDetailModule());
        getCampaignDetails = injector.getInstance(GetCampaignDetails.class);
    }

    @Test
    public void shouldReturnCampaignWithIdWhilePlayer() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(responseJson, true);
        Assertions.assertTrue(((campaignDetailsResponse.getCampaign() != null) && (campaignDetailsResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDM() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(responseJson, false);
        Assertions.assertTrue(((campaignDetailsResponse.getCampaign() != null) && (campaignDetailsResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDMWhileVisibilityFalse() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(responseJson, false);
        Assertions.assertTrue(((campaignDetailsResponse.getCampaign() != null) && (campaignDetailsResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithoutIdWhilePlayerWhileVisibilityFalse() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(responseJson, true);
        Assertions.assertTrue(((campaignDetailsResponse.getCampaign() != null) && (campaignDetailsResponse.getCampaign().getId() == null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCampaignWhenEmptyJson() {
        String responseJson = "{}";
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(responseJson, true);
        Assertions.assertNull(campaignDetailsResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnNoCampaignWhenNullJson() {
        CampaignDetailsResponse campaignDetailsResponse = mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(null, true);
        Assertions.assertNull(campaignDetailsResponse.getCampaign(), "Campaign not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignId, Visibility idVisibility) {
        StringBuilder responseJson = new StringBuilder("{\"campaignDetails\":");
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
        return responseJson.toString();
    }

    private CampaignDetailsResponse mockJsonResponseAsPlayerOrDMAndReturnCampaignDetailsResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
        Player player;
        if (isPlayer)
            player = Player
                    .builder()
                    .build();
        else player = DungeonMaster
                .builder()
                .build();
        CampaignDetailsRequest campaignDetailsRequest = CampaignDetailsRequest
                .builder()
                .campaign(Campaign
                        .builder()
                        .build())
                .player(player)
                .build();
        return getCampaignDetails.getCampaignDetailsResponse(campaignDetailsRequest);
    }
}