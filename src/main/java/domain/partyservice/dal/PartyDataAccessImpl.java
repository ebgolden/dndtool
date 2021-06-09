package domain.partyservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;
import domain.partyservice.dal.dao.*;

public class PartyDataAccessImpl implements PartyDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;

    public PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao) {
        String partyAndCharacterJson = partyAndCharacterDao.getPartyAndCharacterJson();
        requestQueryRequest = constructQueryRequest(partyAndCharacterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String partyJson = requestQueryResponse.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
    }

    public SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao) {
        String partyAndSplitPartiesJson = partyAndSplitPartiesDao.getPartyAndSplitPartiesJson();
        requestQueryRequest = constructQueryRequest(partyAndSplitPartiesJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String splitPartiesJson = requestQueryResponse.getResponseJson();
        return partyDataAccessConverter.getSplitPartiesDaoFromSplitPartiesJson(splitPartiesJson);
    }

    public PartyDao getPartyDao(PartiesDao partiesDao) {
        String partiesJson = partiesDao.getPartiesJson();
        requestQueryRequest = constructQueryRequest(partiesJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String partyJson = requestQueryResponse.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
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