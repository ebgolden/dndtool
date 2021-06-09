package domain.resultservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;
import domain.resultservice.dal.dao.ResultDao;
import domain.resultservice.dal.dao.ResultAndVisibilityDao;

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
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}