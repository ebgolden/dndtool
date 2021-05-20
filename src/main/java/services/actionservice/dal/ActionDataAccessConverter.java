package services.actionservice.dal;

import services.actionservice.bll.bo.*;
import services.actionservice.dal.dao.*;

public interface ActionDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterBo(CharacterBo characterBo);

    ActionAndDiceRollsAndCharacterDao getActionAndDiceRollsAndCharacterDaoFromActionAndDiceRollsBo(ActionAndDiceRollsAndCharacterAndPlayerBo actionAndDiceRollsAndCharacterAndPlayerBo);

    NonStandardActionAndCharacterDao getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);

    ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao);

    ResultBo getResultBoFromResultDao(ResultDao resultDao);

    ActionBo getActionBoFromActionDao(ActionDao actionDao);

    ActionsDao getActionsDaoFromLatestObjectJson(String latestObjectJson);

    ResultDao getResultDaoFromResultObjectJson(String resultObjectJson);

    ActionDao getActionDaoFromActionObjectJson(String actionObjectJson);
}