package services.actionservice.bll;

import services.actionservice.*;
import services.actionservice.bll.bo.*;

public interface ActionBusinessLogicConverter {
    CharacterAndPlayerBo getCharacterAndPlayerBoFromActionsRequest(ActionsRequest actionsRequest);

    ActionAndDiceAndCharacterAndPlayerBo getActionAndDiceAndCharacterAndPlayerBoFromTakeActionRequest(TakeActionRequest takeActionRequest);

    NonStandardActionAndCharacterAndPlayerBo getNonStandardActionAndCharacterAndPlayerBoFromActionFromNonStandardActionRequest(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest);

    ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo);

    TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo);

    ActionFromNonStandardActionResponse getActionFromNonStandardActionResponseFromActionBo(ActionBo actionBo);
}