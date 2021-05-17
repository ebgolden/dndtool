package services.worlddetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;

public class WorldDetailDataAccessImpl implements WorldDetailDataAccess {
    @Inject
    private WorldDetailDataAccessConverter worldDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        dataOperator.sendRequestJson(worldJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return worldDetailDataAccessConverter.getWorldDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }
}