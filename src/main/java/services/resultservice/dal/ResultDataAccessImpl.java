package services.resultservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;

public class ResultDataAccessImpl implements ResultDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private ResultDataAccessConverter resultDataAccessConverter;

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, resultJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String resultAndVisibilityJson = requestQueryResponse.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao) {
        String resultAndVisibilityJson = resultAndVisibilityDao.getResultAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, resultAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        resultAndVisibilityJson = requestQueryResponse.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}