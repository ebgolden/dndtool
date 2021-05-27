package services.worldservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;

public class WorldDataAccessImpl implements WorldDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private WorldDataAccessConverter worldDataAccessConverter;

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        dataOperator.sendRequestJson(api, worldJson);
        String worldAndVisibilityJson = dataOperator.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao) {
        String worldAndVisibilityJson = worldAndVisibilityDao.getWorldAndVisibilityJson();
        dataOperator.sendRequestJson(api, worldAndVisibilityJson);
        worldAndVisibilityJson = dataOperator.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }
}