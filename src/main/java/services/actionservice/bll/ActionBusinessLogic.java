package services.actionservice.bll;

import services.actionservice.bll.bo.*;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterBo characterBo);

    ResultBo getResultBo(ActionAndDiceRollsAndCharacterAndPlayerBo actionAndDiceRollsAndCharacterAndPlayerBo);

    ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);
}