package services.locationservice.dal;

import services.locationservice.bll.bo.LocationAndPlayerBo;
import services.locationservice.bll.bo.LocationAndVisibilityBo;
import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccessConverter {
    LocationDao getLocationDaoFromLocationAndPlayerBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo);

    LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityJson(String locationAndVisibilityJson);
}