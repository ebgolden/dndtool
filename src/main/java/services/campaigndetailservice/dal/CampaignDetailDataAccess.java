package services.campaigndetailservice.dal;

import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;

public interface CampaignDetailDataAccess {
    CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDao campaignDao);

    CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao);
}