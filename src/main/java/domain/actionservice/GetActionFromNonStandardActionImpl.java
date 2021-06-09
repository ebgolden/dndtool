package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionBo;
import domain.actionservice.bll.bo.NonStandardActionAndCharacterAndPlayerBo;

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