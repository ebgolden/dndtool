package services.campaigndetailservice;

import com.google.inject.Inject;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogic;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverter;
import services.campaigndetailservice.bll.bo.CampaignAndPlayerBo;
import services.campaigndetailservice.bll.bo.CampaignDetailsAndVisibilityBo;

public class GetCampaignDetailsImpl implements GetCampaignDetails {
    @Inject
    private CampaignDetailBusinessLogicConverter campaignDetailBusinessLogicConverter;
    @Inject
    private CampaignDetailBusinessLogic campaignDetailBusinessLogic;

    public CampaignDetailsResponse getCampaignDetailsResponse(CampaignDetailsRequest campaignDetailsRequest) {
        CampaignAndPlayerBo campaignAndPlayerBo = campaignDetailBusinessLogicConverter.getCampaignAndPlayerBoFromCampaignDetailsRequest(campaignDetailsRequest);
        CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo = campaignDetailBusinessLogic.getCampaignDetailsAndVisibilityBo(campaignAndPlayerBo);
        return campaignDetailBusinessLogicConverter.getCampaignDetailsResponseFromCampaignDetailsAndVisibilityBo(campaignDetailsAndVisibilityBo);
    }
}