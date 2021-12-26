package domain.campaignservice.bll;

import domain.campaignservice.bll.bo.*;

public interface CampaignBusinessLogic {
    CampaignBo getCampaignBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);

    CampaignAndVisibilityBo getCampaignAndVisibilityBo(CampaignAndPlayerBo campaignAndPlayerBo);

    CampaignAndVisibilityBo getCampaignAndVisibilityBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);

    CampaignBo getCampaignBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo);
}