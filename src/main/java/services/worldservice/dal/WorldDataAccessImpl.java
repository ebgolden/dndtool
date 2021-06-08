package services.worldservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;

public class WorldDataAccessImpl implements WorldDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private WorldDataAccessConverter worldDataAccessConverter;

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldDao worldDao) {
        String worldJson = worldDao.getWorldJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, worldJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String worldAndVisibilityJson = requestQueryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    public WorldAndVisibilityDao getWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao) {
        String worldAndVisibilityJson = worldAndVisibilityDao.getWorldAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, worldAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        worldAndVisibilityJson = requestQueryResponse.getResponseJson();
        return worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityJson(worldAndVisibilityJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}