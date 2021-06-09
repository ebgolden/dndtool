package domain.campaignservice;

import com.google.inject.Inject;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import domain.campaignservice.bll.bo.CampaignAndVisibilityBo;

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