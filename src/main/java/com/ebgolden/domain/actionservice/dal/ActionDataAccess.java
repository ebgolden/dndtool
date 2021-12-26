package com.ebgolden.domain.actionservice.dal;

import com.ebgolden.domain.actionservice.dal.dao.*;

public interface ActionDataAccess {
    ActionsDao getActionsDao(CharacterDao characterDao);

    ResultDao getResultDao(ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao);

    ActionDao getActionDao(NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao);

    ActionAndVisibilityDao getActionAndVisibilityDao(ActionDao actionDao);

    ActionAndVisibilityDao getActionAndVisibilityDao(ActionAndVisibilityDao actionAndVisibilityDao);
}