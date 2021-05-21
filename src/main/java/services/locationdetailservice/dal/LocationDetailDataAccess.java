package services.locationdetailservice.dal;

import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

public interface LocationDetailDataAccess {
    LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDao locationDao);

    LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao);
}