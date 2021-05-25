package services.campaigndetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.campaigndetailservice.dal.dao.CampaignDao;
import services.campaigndetailservice.dal.dao.CampaignDetailsAndVisibilityDao;

public class CampaignDetailDataAccessImpl implements CampaignDetailDataAccess {
    @Inject
    private CampaignDetailDataAccessConverter campaignDetailBusinessLogicConverter;
    @Inject
    private DataOperator dataOperator;

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDao campaignDao) {
        String campaignJson = campaignDao.getCampaignJson();
        dataOperator.sendRequestJson(campaignJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public CampaignDetailsAndVisibilityDao getCampaignDetailsAndVisibilityDao(CampaignDetailsAndVisibilityDao campaignDetailsAndVisibilityDao) {
        String campaignAndVisibilityJson = campaignDetailsAndVisibilityDao.getCampaignDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(campaignAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return campaignDetailBusinessLogicConverter.getCampaignDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}