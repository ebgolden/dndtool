package services.worlddetailservice.dal;

import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;

public interface WorldDetailDataAccessConverter {
    WorldDao getWorldDaoFromWorldAndPlayerBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityDao(WorldDetailsAndVisibilityDao worldDetailsAndVisibilityDao);

    WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject);
}