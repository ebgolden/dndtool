package services.worlddetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;

public class WorldDetailDataAccessImpl implements WorldDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private WorldDetailDataAccessConverter worldDetailDataAccessConverter;

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        dataOperator.sendRequestJson(api, worldJson);
        String worldDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return worldDetailDataAccessConverter.getWorldDetailsAndVisibilityDaoFromWorldDetailsAndVisibilityJson(worldDetailsAndVisibilityJson);
    }

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDao(WorldDetailsAndVisibilityDao worldDetailsAndVisibilityDao) {
        String worldAndVisibilityJson = worldDetailsAndVisibilityDao.getWorldDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, worldAndVisibilityJson);
        String worldDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return worldDetailDataAccessConverter.getWorldDetailsAndVisibilityDaoFromWorldDetailsAndVisibilityJson(worldDetailsAndVisibilityJson);
    }
}