package services.campaignservice.bll;

import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public interface CampaignBusinessLogic {
    CampaignBo getCampaignBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);
}