package services.dataoperatorservice.dal;

import com.google.inject.Inject;
import commonobjects.DataOperator;
import commonobjects.DataOperatorRequestQuery;
import commonobjects.DataOperatorResponseQuery;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;

public class DataOperatorDataAccessImpl implements DataOperatorDataAccess {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperatorDataAccessConverter.getDataOperatorResponseQueryFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorResponseQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }
}