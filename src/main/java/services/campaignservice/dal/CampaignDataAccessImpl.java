package services.campaignservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndVisibilityAndDungeonMasterDao.getCampaignAndVisibilityAndDungeonMasterJson();
        dataOperator.sendRequestJson(campaignAndDungeonMasterJson);
        String campaignJsonObject = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJsonObject(campaignJsonObject);
    }
}