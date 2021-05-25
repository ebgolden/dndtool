package services.campaignservice.bll;

import services.campaignservice.CreateCampaignRequest;
import services.campaignservice.CreateCampaignResponse;
import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public interface CampaignBusinessLogicConverter {
    CampaignAndDungeonMasterBo getCampaignAndDungeonMasterBoFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest);

    CreateCampaignResponse getCreateCampaignResponseFromCampaignBo(CampaignBo campaignBo);
}