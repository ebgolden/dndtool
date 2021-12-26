package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionBo;
import domain.actionservice.bll.bo.NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo;

public class GetActionFromNonStandardActionImpl implements GetActionFromNonStandardAction {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public ActionFromNonStandardActionResponse getActionFromNonStandardActionResponse(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest) {
        NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo = actionBusinessLogicConverter.getNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBoFromActionFromNonStandardActionRequest(actionFromNonStandardActionRequest);
        ActionBo actionBo = actionBusinessLogic.getActionBo(nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo);
        return actionBusinessLogicConverter.getActionFromNonStandardActionResponseFromActionBo(actionBo);
    }
}