package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public class CreateCampaignImpl implements CreateCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public CreateCampaignResponse getCreateCampaignResponse(CreateCampaignRequest createCampaignRequest) {
        CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndVisibilityAndDungeonMasterBoFromCreateCampaignRequest(createCampaignRequest);
        CampaignBo campaignBo = campaignBusinessLogic.getCampaignBo(campaignAndVisibilityAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getCreateCampaignResponseFromCampaignBo(campaignBo);
    }
}