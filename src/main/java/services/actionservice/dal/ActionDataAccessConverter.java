package services.actionservice.dal;

import services.actionservice.bll.bo.*;
import services.actionservice.dal.dao.*;

public interface ActionDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo);

    ActionAndDiceAndCharacterDao getActionAndDiceAndCharacterDaoFromActionAndDiceAndCharacterAndPlayerBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo);

    NonStandardActionAndCharacterDao getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);

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