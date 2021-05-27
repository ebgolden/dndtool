package services.locationservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;

public class LocationDataAccessImpl implements LocationDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private LocationDataAccessConverter locationDataAccessConverter;

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        dataOperator.sendRequestJson(api, locationJson);
        String locationAndVisibilityJson = dataOperator.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
    }

    public LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao) {
        String locationAndVisibilityJson = locationAndVisibilityDao.getLocationAndVisibilityJson();
        dataOperator.sendRequestJson(api, locationAndVisibilityJson);
        locationAndVisibilityJson = dataOperator.getResponseJson();
        return locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityJson(locationAndVisibilityJson);
    }
}