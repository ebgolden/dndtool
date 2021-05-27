package services.locationdetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

public class LocationDetailDataAccessImpl implements LocationDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private LocationDetailDataAccessConverter locationDetailDataAccessConverter;

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        dataOperator.sendRequestJson(api, locationJson);
        String locationDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson);
    }

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao) {
        String locationAndVisibilityJson = locationDetailsAndVisibilityDao.getLocationDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, locationAndVisibilityJson);
        String locationDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson);
    }
}