package services.locationservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;

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