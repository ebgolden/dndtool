package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionAndDiceAndCharacterAndPlayerBo;
import domain.actionservice.bll.bo.ResultBo;

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