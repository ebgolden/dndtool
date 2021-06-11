package domain.actionservice.bll;

import domain.actionservice.bll.bo.*;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterAndPlayerBo characterAndPlayerBo);

    ResultBo getResultBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo);

    ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo);

    ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndPlayerBo actionAndPlayerBo);

    ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo);
}