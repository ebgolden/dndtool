package domain.worldservice.dal;

import domain.worldservice.bll.bo.WorldAndPlayerBo;
import domain.worldservice.bll.bo.WorldAndVisibilityBo;
import domain.worldservice.dal.dao.WorldDao;
import domain.worldservice.dal.dao.WorldAndVisibilityDao;

public interface WorldDataAccessConverter {
    WorldDao getWorldDaoFromWorldAndPlayerBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo);

    WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao);

    WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityJson(String worldAndVisibilityJson);
}