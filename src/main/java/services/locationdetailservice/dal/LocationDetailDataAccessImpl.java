package services.locationdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

public class LocationDetailDataAccessImpl implements LocationDetailDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private LocationDetailDataAccessConverter locationDetailDataAccessConverter;

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        dataOperator.sendRequestJson(locationJson);
        String locationDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson);
    }

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao) {
        String locationAndVisibilityJson = locationDetailsAndVisibilityDao.getLocationDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(locationAndVisibilityJson);
        String locationDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson);
    }
}