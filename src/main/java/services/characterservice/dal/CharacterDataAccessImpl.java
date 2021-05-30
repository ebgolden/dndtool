package services.characterservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.characterservice.dal.dao.*;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;

public class CharacterDataAccessImpl implements CharacterDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao) {
        String characterAndVisibilityAndPlayerJson = characterAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, characterAndVisibilityAndPlayerJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String characterJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao) {
        String nonPlayableCharacterAndVisibilityAndDungeonMasterJson = nonPlayableCharacterAndVisibilityAndDungeonMasterDao.getNonPlayableCharacterAndVisibilityAndDungeonMasterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, nonPlayableCharacterAndVisibilityAndDungeonMasterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String nonPlayableCharacterJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, characterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String nonPlayableCharacterJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public CharacterDao getCharacterDao(NonPlayableCharacterDao nonPlayableCharacterDao) {
        String nonPlayableCharacterJson = nonPlayableCharacterDao.getNonPlayableCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, nonPlayableCharacterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String characterJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PULL, characterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String characterAndVisibilityJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterAndVisibilityDao characterAndVisibilityDao) {
        String characterAndVisibilityJson = characterAndVisibilityDao.getCharacterAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, characterAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        characterAndVisibilityJson = queryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
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