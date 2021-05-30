package services.diceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

public class DiceDataAccessImpl implements DiceDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private DiceDataAccessConverter diceDataAccessConverter;

    public DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao) {
        String diceAndPlayerJson = diceAndPlayerDao.getDiceAndPlayerJson();
        queryRequest = constructQueryRequest(diceAndPlayerJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String diceJson = queryResponse.getResponseJson();
        return diceDataAccessConverter.getDiceDaoFromDiceJson(diceJson);
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
                .queryType(QueryType.PUSH)
                .requestJson(requestJson)
                .build();
    }
}