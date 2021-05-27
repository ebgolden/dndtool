package services.worldservice.dal;

import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;
import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccessConverter {
    WorldDao getWorldDaoFromWorldAndPlayerBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo);

    WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityJson(String worldAndVisibilityJson);
}