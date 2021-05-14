package services.actionservice.bll;

import com.google.inject.Inject;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.dal.ActionDataAccess;
import services.actionservice.dal.ActionDataAccessConverter;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;

public class ActionBusinessLogicImpl implements ActionBusinessLogic {
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;
    @Inject
    private ActionDataAccess actionDataAccess;

    public ActionsBo getActionsBo(CharacterBo characterBo) {
        CharacterDao characterDao = actionDataAccessConverter.getCharacterDaoFromCharacterBo(characterBo);
        ActionsDao actionsDao = actionDataAccess.getActionsDao(characterDao);
        return actionDataAccessConverter.getActionsBoFromActionsDao(actionsDao);
    }
}