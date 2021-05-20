package services.actionservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actionservice.dal.dao.*;

public class ActionDataAccessImpl implements ActionDataAccess {
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public ActionsDao getActionsDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(characterJson);
        String latestObjectJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionsDaoFromLatestObjectJson(latestObjectJson);
    }

    public ResultDao getResultDao(ActionAndDiceRollsAndCharacterDao actionAndDiceRollsAndCharacterDao) {
        String actionAndDiceRollsAndCharacterJson = actionAndDiceRollsAndCharacterDao.getActionAndDiceRollsAndCharacterJson();
        dataOperator.sendRequestJson(actionAndDiceRollsAndCharacterJson);
        String resultObjectJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultObjectJson(resultObjectJson);
    }

    public ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao) {
        String nonStandardActionAndCharacterJson = nonStandardActionAndCharacterDao.getNonStandardActionAndCharacterJson();
        dataOperator.sendRequestJson(nonStandardActionAndCharacterJson);
        String actionObjectJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getActionDaoFromActionObjectJson(actionObjectJson);
    }
}