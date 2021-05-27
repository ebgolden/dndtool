package services.worldservice.dal;

import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccess {
    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao);

    WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);
}