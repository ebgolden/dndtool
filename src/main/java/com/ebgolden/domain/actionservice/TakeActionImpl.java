package com.ebgolden.domain.actionservice;

import com.google.inject.Inject;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogic;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverter;
import com.ebgolden.domain.actionservice.bll.bo.ActionAndDiceAndCharacterAndPlayerBo;
import com.ebgolden.domain.actionservice.bll.bo.ResultBo;

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