package services.raceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.raceservice.dal.dao.RaceDao;

public class RaceDataAccessImpl implements RaceDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private RaceDataAccessConverter raceDataAccessConverter;

    public RaceDao getRaceDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        queryRequest = constructQueryRequest(raceJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        raceJson = queryResponse.getResponseJson();
        return raceDataAccessConverter.getRaceDaoFromRaceJson(raceJson);
    }

    private QueryRequest constructQueryRequest(String requestJson) {
        Campaign campaign = queryRequest.getCampaign();
        Player senderPlayer = queryRequest.getSenderPlayer();
        Object api = queryRequest.getApi();
        return QueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(QueryType.PULL)
                .requestJson(requestJson)
                .build();
    }
}