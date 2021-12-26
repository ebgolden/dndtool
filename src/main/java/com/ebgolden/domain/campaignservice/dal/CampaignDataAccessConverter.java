package com.ebgolden.domain.campaignservice.dal;

import com.ebgolden.domain.campaignservice.bll.bo.*;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndPlayerDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignAndVisibilityDao;
import com.ebgolden.domain.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccessConverter {
    CampaignAndVisibilityAndDungeonMasterDao getCampaignAndVisibilityAndDungeonMasterDaoFromCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);

    CampaignDao getCampaignDaoFromCampaignAndPlayerBo(CampaignAndPlayerBo campaignAndPlayerBo);

    CampaignAndVisibilityDao getCampaignAndVisibilityDaoFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo);

    CampaignAndPlayerDao getCampaignAndPlayerDaoFromCampaignAndPlayerAndDungeonMasterBo(CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo);

    CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao);

    CampaignAndVisibilityBo getCampaignAndVisibilityBoFromCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao);

    CampaignDao getCampaignDaoFromCampaignJson(String campaignJson);

    CampaignAndVisibilityDao getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(String campaignAndVisibilityJson);
}