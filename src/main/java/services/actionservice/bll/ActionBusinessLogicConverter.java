package services.actionservice.bll;

import services.actionservice.*;
import services.actionservice.bll.bo.*;

public interface ActionBusinessLogicConverter {
    CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest);

    ActionAndDiceRollsAndCharacterAndPlayerBo getActionAndDiceRollsAndCharacterAndPlayerBoFromTakeActionRequest(TakeActionRequest takeActionRequest);

    NonStandardActionAndCharacterAndPlayerBo getNonStandardActionAndCharacterAndPlayerBoFromActionFromNonStandardActionRequest(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest);

    ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo);

    TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo);

    ActionFromNonStandardActionResponse getActionFromNonStandardActionResponseFromActionBo(ActionBo actionBo);
}