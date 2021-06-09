package domain.worldservice.dal;

import domain.worldservice.dal.dao.WorldDao;
import domain.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccess {
    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao);

    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);
}