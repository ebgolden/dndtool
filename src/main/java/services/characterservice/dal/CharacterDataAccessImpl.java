package services.characterservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.characterservice.dal.dao.*;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.SendRequestQuery;

public class CharacterDataAccessImpl implements CharacterDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao) {
        String characterAndVisibilityAndPlayerJson = characterAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, characterAndVisibilityAndPlayerJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String characterJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao) {
        String nonPlayableCharacterAndVisibilityAndDungeonMasterJson = nonPlayableCharacterAndVisibilityAndDungeonMasterDao.getNonPlayableCharacterAndVisibilityAndDungeonMasterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, nonPlayableCharacterAndVisibilityAndDungeonMasterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String nonPlayableCharacterJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, characterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String nonPlayableCharacterJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public CharacterDao getCharacterDao(NonPlayableCharacterDao nonPlayableCharacterDao) {
        String nonPlayableCharacterJson = nonPlayableCharacterDao.getNonPlayableCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, nonPlayableCharacterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String characterJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, characterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String characterAndVisibilityJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterAndVisibilityDao characterAndVisibilityDao) {
        String characterAndVisibilityJson = characterAndVisibilityDao.getCharacterAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, characterAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        characterAndVisibilityJson = requestQueryResponse.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .senderPlayer(senderPlayer)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}