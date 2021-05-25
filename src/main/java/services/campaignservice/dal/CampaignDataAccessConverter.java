package services.campaignservice.dal;

import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.dao.CampaignAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccessConverter {
    CampaignAndDungeonMasterDao getCampaignAndDungeonMasterDaoFromCampaignAndDungeonMasterBo(CampaignAndDungeonMasterBo campaignAndDungeonMasterBo);

    CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao);

    CampaignDao getCampaignDaoFromCampaignJsonObject(String campaignJsonObject);
}