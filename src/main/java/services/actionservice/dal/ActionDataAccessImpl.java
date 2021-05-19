package services.actionservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actionservice.dal.dao.ActionAndDiceRollsDao;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;
import services.actionservice.dal.dao.ResultDao;

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

    public ResultDao getResultDao(ActionAndDiceRollsDao actionAndDiceRollsDao) {
        String actionAndDiceRollsJson = actionAndDiceRollsDao.getActionAndDiceRollsJson();
        dataOperator.sendRequestJson(actionAndDiceRollsJson);
        String resultObjectJson = dataOperator.getResponseJson();
        return actionDataAccessConverter.getResultDaoFromResultObjectJson(resultObjectJson);
    }
}