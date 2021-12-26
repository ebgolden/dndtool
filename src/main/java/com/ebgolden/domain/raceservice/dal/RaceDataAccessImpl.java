package com.ebgolden.domain.raceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.raceservice.dal.dao.RaceDao;

public class RaceDataAccessImpl implements RaceDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private RaceDataAccessConverter raceDataAccessConverter;

    public RaceDao getRaceDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        requestQueryRequest = constructQueryRequest(raceJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        raceJson = requestQueryResponse.getResponseJson();
        return raceDataAccessConverter.getRaceDaoFromRaceJson(raceJson);
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