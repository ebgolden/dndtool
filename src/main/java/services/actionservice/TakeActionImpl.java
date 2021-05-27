package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionAndDiceAndCharacterAndPlayerBo;
import services.actionservice.bll.bo.ResultBo;

public class TakeActionImpl implements TakeAction {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public TakeActionResponse getTakeActionResponse(TakeActionRequest takeActionRequest) {
        ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo = actionBusinessLogicConverter.getActionAndDiceAndCharacterAndPlayerBoFromTakeActionRequest(takeActionRequest);
        ResultBo resultBo = actionBusinessLogic.getResultBo(actionAndDiceAndCharacterAndPlayerBo);
        return actionBusinessLogicConverter.getTakeActionResponseFromResultBo(resultBo);
    }
}