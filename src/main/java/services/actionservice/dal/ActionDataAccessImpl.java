package services.actionservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.actionservice.dal.dao.*;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.SendQuery;

public class ActionDataAccessImpl implements ActionDataAccess {
    @Inject
    @Named("queryRequest")
    private QueryRequest queryRequest;
    @Inject
    private SendQuery sendQuery;
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;

    public ActionsDao getActionsDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PULL, characterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String actionsJson = queryResponse.getResponseJson();
        return actionDataAccessConverter.getActionsDaoFromActionsJson(actionsJson);
    }

    public ResultDao getResultDao(ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao) {
        String actionAndDiceAndCharacterJson = actionAndDiceAndCharacterDao.getActionAndDiceAndCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, actionAndDiceAndCharacterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String resultJson = queryResponse.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultJson(resultJson);
    }

    public ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao) {
        String nonStandardActionAndCharacterJson = nonStandardActionAndCharacterDao.getNonStandardActionAndCharacterJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, nonStandardActionAndCharacterJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String actionJson = queryResponse.getResponseJson();
        return actionDataAccessConverter.getActionDaoFromActionJson(actionJson);
    }

    public ActionAndVisibilityDao getActionAndVisibilityDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        queryRequest = constructQueryRequest(QueryType.PULL, actionJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        String actionAndVisibilityJson = queryResponse.getResponseJson();
        return actionDataAccessConverter.getActionAndVisibilityDaoFromActionAndVisibilityJson(actionAndVisibilityJson);
    }

    public ActionAndVisibilityDao getActionAndVisibilityDao(ActionAndVisibilityDao actionAndVisibilityDao) {
        String actionAndVisibilityJson = actionAndVisibilityDao.getActionAndVisibilityJson();
        queryRequest = constructQueryRequest(QueryType.PUSH, actionAndVisibilityJson);
        QueryResponse queryResponse = sendQuery.getQueryResponse(queryRequest);
        actionAndVisibilityJson = queryResponse.getResponseJson();
        return actionDataAccessConverter.getActionAndVisibilityDaoFromActionAndVisibilityJson(actionAndVisibilityJson);
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