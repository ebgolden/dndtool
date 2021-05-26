package services.campaigndetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.campaigndetailservice.dal.dao.CampaignAndPlayerDao;
import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsDao;

public class CampaignDetailDataAccessImpl implements CampaignDetailDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CampaignDetailDataAccessConverter campaignDetailBusinessLogicConverter;

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        dataOperator.sendRequestJson(campaignJson);
        String campaignDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityDaoFromCampaignDetailsAndVisibilityJson(campaignDetailsAndVisibilityJson);
    }

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignDetailsAndVisibilityDao.getCampaignDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(campaignAndVisibilityJson);
        String campaignDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityDaoFromCampaignDetailsAndVisibilityJson(campaignDetailsAndVisibilityJson);
    }

    public CampaignDetailsDao getCampaignDetailsDao(CampaignAndPlayerDao campaignAndPlayerDao) {
        String campaignAndPlayerJson = campaignAndPlayerDao.getCampaignDetailsAndPlayerJson();
        dataOperator.sendRequestJson(campaignAndPlayerJson);
        String campaignDetailsJson = dataOperator.getResponseJson();
        return campaignDetailBusinessLogicConverter.getCampaignDetailsDaoFromCampaignDetailsJson(campaignDetailsJson);
    }
}