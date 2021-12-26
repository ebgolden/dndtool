package com.ebgolden.domain.locationservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.locationservice.dal.dao.LocationDao;
import com.ebgolden.domain.locationservice.dal.dao.LocationAndVisibilityDao;

public class LocationDataAccessImpl implements LocationDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private LocationDataAccessConverter locationDataAccessConverter;

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, locationJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String locationAndVisibilityJson = requestQueryResponse.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
    }

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao) {
        String locationAndVisibilityJson = locationAndVisibilityDao.getLocationAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, locationAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        locationAndVisibilityJson = requestQueryResponse.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
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