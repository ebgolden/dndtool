package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionBo;
import services.actionservice.bll.bo.NonStandardActionAndCharacterAndPlayerBo;

public class GetActionFromNonStandardActionImpl implements GetActionFromNonStandardAction {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public ActionFromNonStandardActionResponse getActionFromNonStandardActionResponse(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest) {
        NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo = actionBusinessLogicConverter.getNonStandardActionAndCharacterAndPlayerBoFromActionFromNonStandardActionRequest(actionFromNonStandardActionRequest);
        ActionBo actionBo = actionBusinessLogic.getActionBo(nonStandardActionAndCharacterAndPlayerBo);
        return actionBusinessLogicConverter.getActionFromNonStandardActionResponseFromActionBo(actionBo);
    }
}