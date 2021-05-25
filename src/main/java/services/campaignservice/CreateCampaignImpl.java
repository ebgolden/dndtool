package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public class CreateCampaignImpl implements CreateCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public CreateCampaignResponse getCreateCampaignResponse(CreateCampaignRequest createCampaignRequest) {
        CampaignAndDungeonMasterBo campaignAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndDungeonMasterBoFromCreateCampaignRequest(createCampaignRequest);
        CampaignBo campaignBo = campaignBusinessLogic.getCampaignBo(campaignAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getCreateCampaignResponseFromCampaignBo(campaignBo);
    }
}