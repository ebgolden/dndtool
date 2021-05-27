package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndPlayerBo;
import services.campaignservice.bll.bo.CampaignAndVisibilityBo;

public class GetUpdatedCampaignImpl implements GetUpdatedCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public UpdatedCampaignResponse getUpdatedCampaignResponse(UpdatedCampaignRequest updatedCampaignRequest) {
        CampaignAndPlayerBo campaignAndPlayerBo = campaignBusinessLogicConverter.getCampaignAndPlayerBoFromUpdatedCampaignRequest(updatedCampaignRequest);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignBusinessLogic.getCampaignAndVisibilityBo(campaignAndPlayerBo);
        return campaignBusinessLogicConverter.getUpdatedCampaignResponseFromCampaignAndVisibilityBo(campaignAndVisibilityBo);
    }
}