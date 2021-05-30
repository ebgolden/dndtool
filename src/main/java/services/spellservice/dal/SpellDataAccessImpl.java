package services.spellservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;
import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;

public class SpellDataAccessImpl implements SpellDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private SpellDataAccessConverter spellDataAccessConverter;

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao) {
        String spellJson = spellDao.getSpellJson();
        queryRequest = constructQueryRequest(QueryType.PULL, spellJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String spellAndVisibilityJson = queryResponse.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
    }

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao) {
        String spellAndVisibilityJson = spellAndVisibilityDao.getSpellAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, spellAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        spellAndVisibilityJson = queryResponse.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
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