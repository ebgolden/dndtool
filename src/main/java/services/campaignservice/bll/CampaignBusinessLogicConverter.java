package services.campaignservice.bll;

import services.campaignservice.CreateCampaignRequest;
import services.campaignservice.CreateCampaignResponse;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public interface CampaignBusinessLogicConverter {
    CampaignAndVisibilityAndDungeonMasterBo getCampaignAndVisibilityAndDungeonMasterBoFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest);

    CreateCampaignResponse getCreateCampaignResponseFromCampaignBo(CampaignBo campaignBo);
}