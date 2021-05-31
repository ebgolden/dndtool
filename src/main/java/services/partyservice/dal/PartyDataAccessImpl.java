package services.partyservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;
import services.partyservice.dal.dao.*;

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
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(QueryType.PUSH)
                .requestJson(requestJson)
                .build();
    }
}