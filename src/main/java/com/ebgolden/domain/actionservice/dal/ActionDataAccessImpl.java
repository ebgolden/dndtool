package domain.actionservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import common.Campaign;
import common.Player;
import common.QueryType;
import domain.actionservice.dal.dao.*;
import persistence.operatorservice.RequestQueryRequest;
import persistence.operatorservice.RequestQueryResponse;
import persistence.operatorservice.SendRequestQuery;

public class ActionDataAccessImpl implements ActionDataAccess {
    @Inject
    @Named("requestQueryRequest")
    private RequestQueryRequest requestQueryRequest;
    @Inject
    private SendRequestQuery sendRequestQuery;
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;

    public ActionsDao getActionsDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, characterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String actionsJson = requestQueryResponse.getResponseJson();
        return actionDataAccessConverter.getActionsDaoFromActionsJson(actionsJson);
    }

    public ResultDao getResultDao(ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao) {
        String actionAndDiceAndCharacterJson = actionAndDiceAndCharacterDao.getActionAndDiceAndCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, actionAndDiceAndCharacterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String resultJson = requestQueryResponse.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultJson(resultJson);
    }

    public ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao) {
        String nonStandardActionAndCharacterJson = nonStandardActionAndCharacterDao.getNonStandardActionAndCharacterJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, nonStandardActionAndCharacterJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String actionJson = requestQueryResponse.getResponseJson();
        return actionDataAccessConverter.getActionDaoFromActionJson(actionJson);
    }

    public ActionAndVisibilityDao getActionAndVisibilityDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        requestQueryRequest = constructQueryRequest(QueryType.PULL, actionJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        String actionAndVisibilityJson = requestQueryResponse.getResponseJson();
        return actionDataAccessConverter.getActionAndVisibilityDaoFromActionAndVisibilityJson(actionAndVisibilityJson);
    }

    public ActionAndVisibilityDao getActionAndVisibilityDao(ActionAndVisibilityDao actionAndVisibilityDao) {
        String actionAndVisibilityJson = actionAndVisibilityDao.getActionAndVisibilityJson();
        requestQueryRequest = constructQueryRequest(QueryType.PUSH, actionAndVisibilityJson);
        RequestQueryResponse requestQueryResponse = sendRequestQuery.getRequestQueryResponse(requestQueryRequest);
        actionAndVisibilityJson = requestQueryResponse.getResponseJson();
        return actionDataAccessConverter.getActionAndVisibilityDaoFromActionAndVisibilityJson(actionAndVisibilityJson);
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