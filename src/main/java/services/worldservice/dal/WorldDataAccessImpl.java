package services.worldservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;

public class WorldDataAccessImpl implements WorldDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private WorldDataAccessConverter worldDataAccessConverter;

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        queryRequest = constructQueryRequest(QueryType.PULL, worldJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String worldAndVisibilityJson = queryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao) {
        String worldAndVisibilityJson = worldAndVisibilityDao.getWorldAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, worldAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        worldAndVisibilityJson = queryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    private QueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}