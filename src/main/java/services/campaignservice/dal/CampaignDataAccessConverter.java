package services.campaignservice.dal;

import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public interface CampaignDataAccessConverter {
    CampaignAndVisibilityAndDungeonMasterDao getCampaignAndVisibilityAndDungeonMasterDaoFromCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);

    CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao);

    CampaignDao getCampaignDaoFromCampaignJsonObject(String campaignJsonObject);
}