package services.campaignservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.campaignservice.dal.dao.CampaignAndPlayerDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityAndDungeonMasterDao;
import services.campaignservice.dal.dao.CampaignAndVisibilityDao;
import services.campaignservice.dal.dao.CampaignDao;

public class CampaignDataAccessImpl implements CampaignDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CampaignDataAccessConverter campaignDataAccessConverter;

    public CampaignDao getCampaignDao(CampaignAndVisibilityAndDungeonMasterDao campaignAndVisibilityAndDungeonMasterDao) {
        String campaignAndDungeonMasterJson = campaignAndVisibilityAndDungeonMasterDao.getCampaignAndVisibilityAndDungeonMasterJson();
        dataOperator.sendRequestJson(api, campaignAndDungeonMasterJson);
        String campaignJson = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        dataOperator.sendRequestJson(api, campaignJson);
        String campaignAndVisibilityJson = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignAndVisibilityDao getCampaignAndVisibilityDao(CampaignAndVisibilityDao campaignAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignAndVisibilityDao.getCampaignAndVisibilityJson();
        dataOperator.sendRequestJson(api, campaignAndVisibilityJson);
        campaignAndVisibilityJson = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignAndVisibilityDaoFromCampaignAndVisibilityJson(campaignAndVisibilityJson);
    }

    public CampaignDao getCampaignDao(CampaignAndPlayerDao campaignAndPlayerDao) {
        String campaignAndPlayerJson = campaignAndPlayerDao.getCampaignAndPlayerJson();
        dataOperator.sendRequestJson(api, campaignAndPlayerJson);
        String campaignJson = dataOperator.getResponseJson();
        return campaignDataAccessConverter.getCampaignDaoFromCampaignJson(campaignJson);
    }
}