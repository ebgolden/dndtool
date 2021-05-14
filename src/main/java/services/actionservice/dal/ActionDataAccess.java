package services.actionservice.dal;

import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;

public interface ActionDataAccess {
    ActionsDao getActionsDao(CharacterDao characterDao);
}