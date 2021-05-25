package services.campaignservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.campaignservice.dal.dao.CampaignAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public CampaignDao getCampaignDao(CampaignAndDungeonMasterDao campaignAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndDungeonMasterDao.getCampaignAndDungeonMasterJson();
        dataOperator.sendRequestJson(campaignAndDungeonMasterJson);
        String campaignJsonObject = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJsonObject(campaignJsonObject);
    }
}