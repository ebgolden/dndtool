package com.ebgolden.domain.worldservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.worldservice.dal.dao.WorldDao;
import com.ebgolden.domain.worldservice.dal.dao.WorldAndVisibilityDao;

public class WorldDataAccessImpl implements WorldDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private WorldDataAccessConverter worldDataAccessConverter;

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, worldJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String worldAndVisibilityJson = requestQueryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao) {
        String worldAndVisibilityJson = worldAndVisibilityDao.getWorldAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, worldAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        worldAndVisibilityJson = requestQueryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
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