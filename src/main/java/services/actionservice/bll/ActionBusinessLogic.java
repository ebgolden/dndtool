package services.actionservice.bll;

import services.actionservice.bll.bo.*;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterAndPlayerBo characterAndPlayerBo);

    ResultBo getResultBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo);

    ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);
}