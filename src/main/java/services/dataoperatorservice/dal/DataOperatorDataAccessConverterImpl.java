package services.dataoperatorservice.dal;

import commonobjects.DataOperatorRequestQuery;
import commonobjects.DataOperatorResponseQuery;
import commonobjects.QueryType;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;

public class DataOperatorDataAccessConverterImpl implements DataOperatorDataAccessConverter {
    public CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        String campaignId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getCampaignId();
        String senderPlayerId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getSenderPlayerId();
        String apiName = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getApiName();
        QueryType queryType = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getQueryType();
        String queryTypeName = queryType.getQueryType();
        String requestJson = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getRequestJson();
        return CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
                .apiName(apiName)
                .queryType(queryTypeName)
                .requestJson(requestJson)
                .build();
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        String queryId = queryIdAndResponseJsonDao.getQueryId();
        String responseJson = queryIdAndResponseJsonDao.getResponseJson();
        return QueryIdAndResponseJsonBo
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public DataOperatorRequestQuery getDataOperatorRequestQueryFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        String campaignId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getCampaignId();
        String senderPlayerId = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getSenderPlayerId();
        String apiName = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getApiName();
        String queryType = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getQueryType();
        String requestJson = campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getRequestJson();
        return DataOperatorRequestQuery
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(DataOperatorResponseQuery dataOperatorResponseQuery) {
        String queryId = dataOperatorResponseQuery.getQueryId();
        String responseJson = dataOperatorResponseQuery.getResponseJson();
        return QueryIdAndResponseJsonDao
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }
}