package domain.actionservice.bll;

import domain.actionservice.*;
import domain.actionservice.bll.bo.*;

public interface ActionBusinessLogicConverter {
    CharacterAndPlayerBo getCharacterAndPlayerBoFromActionsRequest(ActionsRequest actionsRequest);

    ActionAndDiceAndCharacterAndPlayerBo getActionAndDiceAndCharacterAndPlayerBoFromTakeActionRequest(TakeActionRequest takeActionRequest);

    NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo getNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBoFromActionFromNonStandardActionRequest(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest);

    ActionAndPlayerBo getActionAndPlayerBoFromUpdatedActionRequest(UpdatedActionRequest updatedActionRequest);

    ActionAndVisibilityAndPlayerBo getActionAndVisibilityAndPlayerBoFromChangeVisibilityOfActionDetailsRequest(ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest);

    ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo);

    TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo);

    ActionFromNonStandardActionResponse getActionFromNonStandardActionResponseFromActionBo(ActionBo actionBo);

    UpdatedActionResponse getUpdatedActionResponseFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo);

    ChangeVisibilityOfActionDetailsResponse getChangeVisibilityOfActionDetailsResponseFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo);

    ActionAndVisibilityBo getActionAndVisibilityBoFromActionAndVisibilityAndPlayerBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo);
}