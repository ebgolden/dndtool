package services.resultservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;

public class ResultDataAccessImpl implements ResultDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private ResultDataAccessConverter resultDataAccessConverter;

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        queryRequest = constructQueryRequest(QueryType.PULL, resultJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String resultAndVisibilityJson = queryResponse.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao) {
        String resultAndVisibilityJson = resultAndVisibilityDao.getResultAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, resultAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        resultAndVisibilityJson = queryResponse.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }

    private QueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}