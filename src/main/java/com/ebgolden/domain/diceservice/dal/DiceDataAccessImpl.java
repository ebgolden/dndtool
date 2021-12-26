package com.ebgolden.domain.diceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.diceservice.dal.dao.DiceAndPlayerDao;
import com.ebgolden.domain.diceservice.dal.dao.DiceDao;

public class DiceDataAccessImpl implements DiceDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private DiceDataAccessConverter diceDataAccessConverter;

    public DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao) {
        String diceAndPlayerJson = diceAndPlayerDao.getDiceAndPlayerJson();
        requestQueryRequest = constructQueryRequest(diceAndPlayerJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String diceJson = requestQueryResponse.getResponseJson();
        return diceDataAccessConverter.getDiceDaoFromDiceJson(diceJson);
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
                .queryType(QueryType.PUSH)
                .requestJson(requestJson)
                .build();
    }
}