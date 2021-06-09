package domain.locationservice.dal;

import domain.locationservice.bll.bo.LocationAndPlayerBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;
import domain.locationservice.dal.dao.LocationDao;
import domain.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccessConverter {
    LocationDao getLocationDaoFromLocationAndPlayerBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo);

    LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityJson(String locationAndVisibilityJson);
}