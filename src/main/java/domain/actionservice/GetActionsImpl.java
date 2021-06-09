package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionsBo;
import domain.actionservice.bll.bo.CharacterAndPlayerBo;

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