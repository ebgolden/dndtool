package services.actionservice.dal;

import services.actionservice.dal.dao.*;

public interface ActionDataAccess {
    ActionsDao getActionsDao(CharacterDao characterDao);

    ResultDao getResultDao(ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao);

    ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao);
}