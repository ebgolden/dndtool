package services.dataoperatorservice.dal;

import com.google.inject.Inject;
import commonobjects.DataOperator;
import commonobjects.DataOperatorRequestQuery;
import commonobjects.DataOperatorResponseQuery;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;

public class DataOperatorDataAccessImpl implements DataOperatorDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }
}