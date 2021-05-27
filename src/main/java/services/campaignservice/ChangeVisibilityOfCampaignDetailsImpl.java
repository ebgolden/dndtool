package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignAndVisibilityBo;

public class ChangeVisibilityOfCampaignDetailsImpl implements ChangeVisibilityOfCampaignDetails {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public ChangeVisibilityOfCampaignDetailsResponse getChangeVisibilityOfCampaignDetailsResponse(ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest) {
        CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfCampaignDetailsRequest(changeVisibilityOfUpdatedCampaignRequest);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignBusinessLogic.getCampaignAndVisibilityBo(campaignAndVisibilityAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getChangeVisibilityOfCampaignDetailsResponseFromCampaignAndVisibilityBo(campaignAndVisibilityBo);
    }
}