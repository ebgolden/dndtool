package services.campaignservice.bll;

import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public interface CampaignBusinessLogic {
    CampaignBo getCampaignBo(CampaignAndDungeonMasterBo campaignAndDungeonMasterBo);
}