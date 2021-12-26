package com.ebgolden.domain.locationservice.dal;

import com.ebgolden.domain.locationservice.dal.dao.LocationDao;
import com.ebgolden.domain.locationservice.dal.dao.LocationAndVisibilityDao;

public interface LocationDataAccess {
    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationDao locationDao);

    LocationAndVisibilityDao getLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao);
}