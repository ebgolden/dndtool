package services.actionservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actionservice.dal.dao.*;

public class ActionDataAccessImpl implements ActionDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;

    public ActionsDao getActionsDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(characterJson);
        String actionsJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionsDaoFromActionsJson(actionsJson);
    }

    public ResultDao getResultDao(ActionAndDiceRollsAndCharacterDao actionAndDiceRollsAndCharacterDao) {
        String actionAndDiceRollsAndCharacterJson = actionAndDiceRollsAndCharacterDao.getActionAndDiceRollsAndCharacterJson();
        dataOperator.sendRequestJson(actionAndDiceRollsAndCharacterJson);
        String resultJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultJson(resultJson);
    }

    public ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao) {
        String nonStandardActionAndCharacterJson = nonStandardActionAndCharacterDao.getNonStandardActionAndCharacterJson();
        dataOperator.sendRequestJson(nonStandardActionAndCharacterJson);
        String actionJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionDaoFromActionJson(actionJson);
    }
}