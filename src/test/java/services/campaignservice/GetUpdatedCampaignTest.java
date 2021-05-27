package services.campaignservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import commonobjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.campaignservice.module.CampaignModule;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUpdatedCampaignTest {
    @Mock
    DataOperator mockDataOperator;
    private GetUpdatedCampaign getUpdatedCampaign;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DataOperator.class).toInstance(mockDataOperator);
            }
        }, new CampaignModule(GetUpdatedCampaign.class));
        getUpdatedCampaign = injector.getInstance(GetUpdatedCampaign.class);
    }

    @Test
    public void shouldReturnCampaignWithIdWhilePlayer() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(responseJson, true);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDM() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.EVERYONE);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(responseJson, false);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithIdWhileDMWhileVisibilityFalse() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(responseJson, false);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() != null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnCampaignWithoutIdWhilePlayerWhileVisibilityFalse() {
        String campaignId = "0";
        String responseJson = createMockResponseJsonWithVisibilityOfId(campaignId, Visibility.DUNGEON_MASTER);
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(responseJson, true);
        Assertions.assertTrue(((updatedCampaignResponse.getCampaign() != null) && (updatedCampaignResponse.getCampaign().getId() == null)), "Campaign null and/or wrong visibility.");
    }

    @Test
    public void shouldReturnNoCampaignWhenEmptyJson() {
        String responseJson = "{}";
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(responseJson, true);
        Assertions.assertNull(updatedCampaignResponse.getCampaign(), "Campaign not null.");
    }

    @Test
    public void shouldReturnNoCampaignWhenNullJson() {
        UpdatedCampaignResponse updatedCampaignResponse = mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(null, true);
        Assertions.assertNull(updatedCampaignResponse.getCampaign(), "Campaign not null.");
    }

    private String createMockResponseJsonWithVisibilityOfId(String campaignId, Visibility idVisibility) {
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
        return responseJson.toString();
    }

    private UpdatedCampaignResponse mockJsonResponseAsPlayerOrDMAndReturnUpdatedCampaignResponse(String responseJson, boolean isPlayer) {
        when(mockDataOperator.getResponseJson()).thenReturn(responseJson);
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