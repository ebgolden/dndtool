package services.actionservice.bll;

import services.actionservice.bll.bo.*;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterBo characterBo);

    ResultBo getResultBo(ActionAndDiceRollsBo actionAndDiceRollsBo);

    ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);
}