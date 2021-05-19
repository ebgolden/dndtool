package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ResultBo;

public class TakeActionImpl implements TakeAction {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public TakeActionResponse getTakeActionResponse(TakeActionRequest takeActionRequest) {
        ActionAndDiceRollsBo actionAndDiceRollsBo = actionBusinessLogicConverter.getActionAndDiceRollsBoFromTakeActionRequest(takeActionRequest);
        ResultBo resultBo = actionBusinessLogic.getResultBo(actionAndDiceRollsBo);
        return actionBusinessLogicConverter.getTakeActionResponseFromResultBo(resultBo);
    }
}