package com.ebgolden.domain.worldservice.dal;

import com.ebgolden.domain.worldservice.dal.dao.WorldDao;
import com.ebgolden.domain.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccess {
    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao);

    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);
}