package services.campaignservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;

    public CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndVisibilityAndDungeonMasterDao.getCampaignAndVisibilityAndDungeonMasterJson();
        dataOperator.sendRequestJson(campaignAndDungeonMasterJson);
        String campaignJson = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }
}