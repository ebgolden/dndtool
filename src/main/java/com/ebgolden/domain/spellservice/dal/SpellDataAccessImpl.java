package com.ebgolden.domain.spellservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.RequestQueryRequest;
import com.ebgolden.persistence.operatorservice.RequestQueryResponse;
import com.ebgolden.persistence.operatorservice.SendRequestQuery;
import com.ebgolden.domain.spellservice.dal.dao.SpellDao;
import com.ebgolden.domain.spellservice.dal.dao.SpellAndVisibilityDao;

public class SpellDataAccessImpl implements SpellDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private SpellDataAccessConverter spellDataAccessConverter;

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao) {
        String spellJson = spellDao.getSpellJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, spellJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String spellAndVisibilityJson = requestQueryResponse.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
    }

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao) {
        String spellAndVisibilityJson = spellAndVisibilityDao.getSpellAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, spellAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        spellAndVisibilityJson = requestQueryResponse.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
    }

    private RequestQueryRequest constructQueryRequest(QueryType queryType, String requestJson) {
        Campaign campaign = requestQueryRequest.getCampaign();
        Player player = requestQueryRequest.getPlayer();
        Object api = requestQueryRequest.getApi();
        return RequestQueryRequest
                .builder()
                .campaign(campaign)
                .player(player)
                .api(api)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }
}