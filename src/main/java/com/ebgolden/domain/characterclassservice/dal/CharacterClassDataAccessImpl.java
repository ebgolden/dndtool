package com.ebgolden.domain.characterclassservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.domain.characterclassservice.dal.dao.CharacterClassDao;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;

public class CharacterClassDataAccessImpl implements CharacterClassDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private CharacterClassDataAccessConverter characterClassDataAccessConverter;

    public CharacterClassDao getCharacterClassDao(CharacterClassDao characterClassDao) {
        String characterClassJson = characterClassDao.getCharacterClassJson();
        requestQueryRequest = constructQueryRequest(characterClassJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        characterClassJson = requestQueryResponse.getResponseJson();
        return characterClassDataAccessConverter.getCharacterClassDaoFromCharacterClassJson(characterClassJson);
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
                .queryType(QueryType.PULL)
                .requestJson(requestJson)
                .build();
    }
}