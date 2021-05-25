package services.campaignservice.dal;

import services.campaignservice.dal.dao.CampaignAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccess {
    CampaignDao getCampaignDao(CampaignAndDungeonMasterDao campaignAndDungeonMasterDao);
}