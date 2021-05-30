package services.partyservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.partyservice.dal.dao.*;

public class PartyDataAccessImpl implements PartyDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;

    public PartyDao getPartyDao(PartyAndCharacterDao partyAndCharacterDao) {
        String partyAndCharacterJson = partyAndCharacterDao.getPartyAndCharacterJson();
        queryRequest = constructQueryRequest(partyAndCharacterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String partyJson = queryResponse.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
    }

    public SplitPartiesDao getSplitPartiesDao(PartyAndSplitPartiesDao partyAndSplitPartiesDao) {
        String partyAndSplitPartiesJson = partyAndSplitPartiesDao.getPartyAndSplitPartiesJson();
        queryRequest = constructQueryRequest(partyAndSplitPartiesJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String splitPartiesJson = queryResponse.getResponseJson();
        return partyDataAccessConverter.getSplitPartiesDaoFromSplitPartiesJson(splitPartiesJson);
    }

    public PartyDao getPartyDao(PartiesDao partiesDao) {
        String partiesJson = partiesDao.getPartiesJson();
        queryRequest = constructQueryRequest(partiesJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String partyJson = queryResponse.getResponseJson();
        return partyDataAccessConverter.getPartyDaoFromPartyJson(partyJson);
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