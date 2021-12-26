package com.ebgolden.domain.resultservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.resultservice.dal.dao.ResultDao;
import com.ebgolden.domain.resultservice.dal.dao.ResultAndVisibilityDao;

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