package services.actionservice.bll;

import services.actionservice.bll.bo.*;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterAndPlayerBo characterAndPlayerBo);

    ResultBo getResultBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo);

    ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo);

    ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndPlayerBo actionAndPlayerBo);

    ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo);
}