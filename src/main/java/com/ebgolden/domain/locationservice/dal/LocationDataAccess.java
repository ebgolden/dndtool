package domain.locationservice.dal;

import domain.locationservice.dal.dao.LocationDao;
import domain.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccess {
    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao);

    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);
}