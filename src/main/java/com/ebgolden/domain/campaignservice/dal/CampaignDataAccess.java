package domain.campaignservice.dal;

import domain.campaignservice.dal.dao.CampaignAndPlayerDao;
import domain.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import domain.campaignservice.dal.dao.CampaignAndVisibilityDao;
import domain.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccess {
    CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao);

    CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao);
}