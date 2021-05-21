package services.locationdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

public class LocationDetailDataAccessImpl implements LocationDetailDataAccess {
    @Inject
    private LocationDetailDataAccessConverter locationDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDao locationDao) {
        String locationJson = locationDao.getLocationJson();
        dataOperator.sendRequestJson(locationJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao) {
        String locationAndVisibilityJson = locationDetailsAndVisibilityDao.getLocationDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(locationAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}