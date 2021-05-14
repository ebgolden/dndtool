package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;

public class GetActionsImpl implements GetActions {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public ActionsResponse getActionsResponse(ActionsRequest actionsRequest) {
        CharacterBo characterBo = actionBusinessLogicConverter.getCharacterBoFromActionsRequest(actionsRequest);
        ActionsBo actionsBo = actionBusinessLogic.getActionsBo(characterBo);
        return actionBusinessLogicConverter.getActionsResponseFromActionsBo(actionsBo);
    }
}