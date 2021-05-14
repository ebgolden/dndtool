package services.actionservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;

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
}