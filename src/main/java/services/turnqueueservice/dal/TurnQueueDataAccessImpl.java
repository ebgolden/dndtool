package services.turnqueueservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueDataAccessImpl implements TurnQueueDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;

    public TurnQueueDao getTurnQueueDao(EncounterDao encounterDao) {
        String encounterJson = encounterDao.getEncounterJson();
        queryRequest = constructQueryRequest(encounterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String turnQueueJson = queryResponse.getResponseJson();
        return turnQueueDataAccessConverter.getTurnQueueDaoFromTurnQueueJson(turnQueueJson);
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