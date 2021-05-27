package services.locationservice.dal;

import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccess {
    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao);

    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);
}