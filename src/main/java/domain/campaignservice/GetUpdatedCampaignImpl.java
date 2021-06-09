package domain.campaignservice;

import com.google.inject.Inject;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.bo.CampaignAndPlayerBo;
import domain.campaignservice.bll.bo.CampaignAndVisibilityBo;

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