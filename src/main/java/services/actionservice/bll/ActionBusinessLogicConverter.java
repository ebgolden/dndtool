package services.actionservice.bll;

import services.actionservice.ActionsRequest;
import services.actionservice.ActionsResponse;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;

public interface ActionBusinessLogicConverter {
    CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest);

    ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo);
}