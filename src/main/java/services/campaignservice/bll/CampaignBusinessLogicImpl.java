package services.campaignservice.bll;

import com.google.inject.Inject;
import objects.*;
import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.CampaignDataAccess;
import services.campaignservice.dal.CampaignDataAccessConverter;
import services.campaignservice.dal.dao.CampaignAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignBusinessLogicImpl implements CampaignBusinessLogic {
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;
    @Inject
    private CampaignDataAccess campaignDataAccess;

    public CampaignBo getCampaignBo(CampaignAndDungeonMasterBo campaignAndDungeonMasterBo) {
        CampaignAndDungeonMasterBo filteredCampaignAndDungeonMasterBo = filterCampaignAndDungeonMasterBo(campaignAndDungeonMasterBo);
        CampaignAndDungeonMasterDao campaignAndDungeonMasterDao = campaignDataAccessConverter.getCampaignAndDungeonMasterDaoFromCampaignAndDungeonMasterBo(filteredCampaignAndDungeonMasterBo);
        CampaignDao campaignDao = campaignDataAccess.getCampaignDao(campaignAndDungeonMasterDao);
        return campaignDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    private CampaignAndDungeonMasterBo filterCampaignAndDungeonMasterBo(CampaignAndDungeonMasterBo campaignAndDungeonMasterBo) {
        Campaign campaign = campaignAndDungeonMasterBo.getCampaign();
        DungeonMaster dungeonMaster = campaignAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId))
            campaign = null;
        return CampaignAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .dungeonMaster(dungeonMaster)
                .build();
    }
}