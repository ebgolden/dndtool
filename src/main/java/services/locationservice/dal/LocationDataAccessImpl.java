package services.locationservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;

public class LocationDataAccessImpl implements LocationDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private LocationDataAccessConverter locationDataAccessConverter;

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        queryRequest = constructQueryRequest(QueryType.PULL, locationJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String locationAndVisibilityJson = queryResponse.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
    }

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao) {
        String locationAndVisibilityJson = locationAndVisibilityDao.getLocationAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, locationAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        locationAndVisibilityJson = queryResponse.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
    }

    private QueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}