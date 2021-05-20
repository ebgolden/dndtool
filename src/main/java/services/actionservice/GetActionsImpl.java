package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterAndPlayerBo;

public class GetActionsImpl implements GetActions {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public ActionsResponse getActionsResponse(ActionsRequest actionsRequest) {
        CharacterAndPlayerBo characterAndPlayerBo = actionBusinessLogicConverter.getCharacterAndPlayerBoFromActionsRequest(actionsRequest);
        ActionsBo actionsBo = actionBusinessLogic.getActionsBo(characterAndPlayerBo);
        return actionBusinessLogicConverter.getActionsResponseFromActionsBo(actionsBo);
    }
}