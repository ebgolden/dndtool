package com.ebgolden.domain.worldservice.dal;

import com.ebgolden.domain.worldservice.bll.bo.WorldAndPlayerBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;
import com.ebgolden.domain.worldservice.dal.dao.WorldDao;
import com.ebgolden.domain.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccessConverter {
    WorldDao getWorldDaoFromWorldAndPlayerBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo);

    WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityJson(String worldAndVisibilityJson);
}