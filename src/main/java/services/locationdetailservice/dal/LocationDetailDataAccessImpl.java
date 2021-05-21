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
        String updatedJsonObject = dataOperator.getResponseJson();
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromUpdatedJsonObject(updatedJsonObject);
    }
}