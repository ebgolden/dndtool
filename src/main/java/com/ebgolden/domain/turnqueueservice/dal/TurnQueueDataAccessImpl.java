package com.ebgolden.domain.turnqueueservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.turnqueueservice.dal.dao.EncounterDao;
import com.ebgolden.domain.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueDataAccessImpl implements TurnQueueDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;

    public TurnQueueDao getTurnQueueDao(EncounterDao encounterDao) {
        String encounterJson = encounterDao.getEncounterJson();
        requestQueryRequest = constructQueryRequest(encounterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String turnQueueJson = requestQueryResponse.getResponseJson();
        return turnQueueDataAccessConverter.getTurnQueueDaoFromTurnQueueJson(turnQueueJson);
    }

    private RequestQueryRequest constructQueryRequest(String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(QueryType.PULL)
                .requestJson(requestJson)
                .build();
    }
}