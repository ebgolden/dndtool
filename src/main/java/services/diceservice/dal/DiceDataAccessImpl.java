package services.diceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

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