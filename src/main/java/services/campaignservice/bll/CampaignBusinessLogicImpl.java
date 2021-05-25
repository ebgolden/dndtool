package services.campaignservice.bll;

import com.google.inject.Inject;
import objects.*;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import services.campaignservice.dal.CampaignDataAccess;
import services.campaignservice.dal.CampaignDataAccessConverter;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;
import java.util.Map;

public class CampaignBusinessLogicImpl implements CampaignBusinessLogic {
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;
    @Inject
    private CampaignDataAccess campaignDataAccess;

    public CampaignBo getCampaignBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        CampaignAndVisibilityAndDungeonMasterBo filteredCampaignAndVisibilityAndDungeonMasterBo = filterCampaignAndDungeonMasterBo(campaignAndVisibilityAndDungeonMasterBo);
        CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao = campaignDataAccessConverter.getCampaignAndVisibilityAndDungeonMasterDaoFromCampaignAndVisibilityAndDungeonMasterBo(filteredCampaignAndVisibilityAndDungeonMasterBo);
        CampaignDao campaignDao = campaignDataAccess.getCampaignDao(campaignAndVisibilityAndDungeonMasterDao);
        return campaignDataAccessConverter.getCampaignBoFromCampaignDao(campaignDao);
    }

    private CampaignAndVisibilityAndDungeonMasterBo filterCampaignAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = campaign.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            campaign = null;
            visibilityMap = null;
        }
        return CampaignAndVisibilityAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }
}