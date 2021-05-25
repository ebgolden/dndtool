package services.campaignservice.dal;

import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccess {
    CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao);
}