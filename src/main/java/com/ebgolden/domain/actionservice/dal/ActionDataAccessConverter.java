package com.ebgolden.domain.actionservice.dal;

import com.ebgolden.domain.actionservice.bll.bo.*;
import com.ebgolden.domain.actionservice.dal.dao.*;

public interface ActionDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo);

    ActionAndDiceAndCharacterDao getActionAndDiceAndCharacterDaoFromActionAndDiceAndCharacterAndPlayerBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo);

    NonStandardActionAndCharacterDao getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo(NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo);

    ActionDao getActionDaoFromActionAndPlayerBo(ActionAndPlayerBo actionAndPlayerBo);

    ActionAndVisibilityDao getActionAndVisibilityDaoFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo);

    ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao);

    ResultBo getResultBoFromResultDao(ResultDao resultDao);

    ActionBo getActionBoFromActionDao(ActionDao actionDao);

    ActionAndVisibilityBo getActionAndVisibilityBoFromActionAndVisibilityDao(ActionAndVisibilityDao actionAndVisibilityDao);

    ActionsDao getActionsDaoFromActionsJson(String actionsJson);

    ResultDao getResultDaoFromResultJson(String resultJson);

    ActionDao getActionDaoFromActionJson(String actionJson);

    ActionAndVisibilityDao getActionAndVisibilityDaoFromActionAndVisibilityJson(String actionAndVisibilityJson);
}