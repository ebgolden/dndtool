package domain.locationservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;
import domain.locationservice.dal.dao.LocationDao;
import domain.locationservice.dal.dao.LocationAndVisibilityDao;

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