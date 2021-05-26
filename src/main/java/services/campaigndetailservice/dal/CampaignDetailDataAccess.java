package services.campaigndetailservice.dal;

import services.campaigndetailservice.dal.dao.CampaignAndPlayerDao;
import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsDao;

public interface CampaignDetailDataAccess {
    CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDao campaignDao);

    CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao);

    CampaignDetailsDao getCampaignDetailsDao(CampaignAndPlayerDao campaignAndPlayerDao);
}