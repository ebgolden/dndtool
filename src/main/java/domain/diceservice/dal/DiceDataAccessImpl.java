package domain.diceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;
import domain.diceservice.dal.dao.DiceAndPlayerDao;
import domain.diceservice.dal.dao.DiceDao;

public class DiceDataAccessImpl implements DiceDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private DiceDataAccessConverter diceDataAccessConverter;

    public DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao) {
        String diceAndPlayerJson = diceAndPlayerDao.getDiceAndPlayerJson();
        requestQueryRequest = constructQueryRequest(diceAndPlayerJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String diceJson = requestQueryResponse.getResponseJson();
        return diceDataAccessConverter.getDiceDaoFromDiceJson(diceJson);
    }

    private RequestQueryRequest constructQueryRequest(String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(QueryType.PUSH)
                .requestJson(requestJson)
                .build();
    }
}