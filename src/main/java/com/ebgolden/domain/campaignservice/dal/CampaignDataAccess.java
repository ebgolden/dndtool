package com.ebgolden.domain.campaignservice.dal;

import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndPlayerDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndVisibilityDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccess {
    CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao);

    CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao);

    CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao);
}