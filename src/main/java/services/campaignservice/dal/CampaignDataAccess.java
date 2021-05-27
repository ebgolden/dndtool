package services.campaignservice.dal;

import services.campaignservice.dal.dao.CampaignAndPlayerDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityDao;
import services.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccess {
    CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao);

    CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao);
}