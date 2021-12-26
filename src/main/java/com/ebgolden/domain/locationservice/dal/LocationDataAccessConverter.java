package com.ebgolden.domain.locationservice.dal;

import com.ebgolden.domain.locationservice.bll.bo.LocationAndPlayerBo;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndVisibilityBo;
import com.ebgolden.domain.locationservice.dal.dao.LocationDao;
import com.ebgolden.domain.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccessConverter {
    LocationDao getLocationDaoFromLocationAndPlayerBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo);

    LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);

    LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityJson(String locationAndVisibilityJson);
}