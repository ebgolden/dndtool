package services.actionservice.dal;

import services.actionservice.dal.dao.ActionAndDiceRollsDao;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;
import services.actionservice.dal.dao.ResultDao;

public interface ActionDataAccess {
    ActionsDao getActionsDao(CharacterDao characterDao);

    ResultDao getResultDao(ActionAndDiceRollsDao actionAndDiceRollsDao);
}