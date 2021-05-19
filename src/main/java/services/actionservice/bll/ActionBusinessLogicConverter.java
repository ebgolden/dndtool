package services.actionservice.bll;

import services.actionservice.ActionsRequest;
import services.actionservice.ActionsResponse;
import services.actionservice.TakeActionRequest;
import services.actionservice.TakeActionResponse;
import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.bll.bo.ResultBo;

public interface ActionBusinessLogicConverter {
    CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest);

    ActionAndDiceRollsBo getActionAndDiceRollsBoFromTakeActionRequest(TakeActionRequest takeActionRequest);

    ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo);

    TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo);
}