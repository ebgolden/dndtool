package services.actionservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.actionservice.dal.dao.*;

public class ActionDataAccessImpl implements ActionDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;

    public ActionsDao getActionsDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(api, characterJson);
        String actionsJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionsDaoFromActionsJson(actionsJson);
    }

    public ResultDao getResultDao(ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao) {
        String actionAndDiceAndCharacterJson = actionAndDiceAndCharacterDao.getActionAndDiceAndCharacterJson();
        dataOperator.sendRequestJson(api, actionAndDiceAndCharacterJson);
        String resultJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultJson(resultJson);
    }

    public ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao) {
        String nonStandardActionAndCharacterJson = nonStandardActionAndCharacterDao.getNonStandardActionAndCharacterJson();
        dataOperator.sendRequestJson(api, nonStandardActionAndCharacterJson);
        String actionJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionDaoFromActionJson(actionJson);
    }
}