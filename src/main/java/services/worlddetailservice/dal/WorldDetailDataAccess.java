package services.worlddetailservice.dal;

import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;

public interface WorldDetailDataAccess {
    WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDao(WorldDao worldDao);
}