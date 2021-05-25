package services.campaigndetailservice;

import com.google.inject.Inject;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogic;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverter;
import services.campaigndetailservice.bll.bo.CampaignDetailsAndVisibilityAndDungeonMasterBo;
import services.campaigndetailservice.bll.bo.CampaignDetailsAndVisibilityBo;

public class UpdateCampaignDetailsVisibilityImpl implements UpdateCampaignDetailsVisibility {
    @Inject
    private CampaignDetailBusinessLogicConverter campaignDetailBusinessLogicConverter;
    @Inject
    private CampaignDetailBusinessLogic campaignDetailBusinessLogic;

    public CampaignDetailsVisibilityResponse getCampaignDetailsVisibilityResponse(CampaignDetailsVisibilityRequest campaignDetailsVisibilityRequest) {
        CampaignDetailsAndVisibilityAndDungeonMasterBo campaignDetailsAndVisibilityAndDungeonMasterBo = campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityAndDungeonMasterBoFromCampaignDetailsVisibilityRequest(campaignDetailsVisibilityRequest);
        CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo = campaignDetailBusinessLogic.getCampaignDetailsAndVisibilityBo(campaignDetailsAndVisibilityAndDungeonMasterBo);
        return campaignDetailBusinessLogicConverter.getCampaignDetailsVisibilityResponseFromCampaignDetailsAndVisibilityBo(campaignDetailsAndVisibilityBo);
    }
}