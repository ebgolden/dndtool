package com.ebgolden.domain.actionservice;

import com.google.inject.Inject;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogic;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverter;
import com.ebgolden.domain.actionservice.bll.bo.ActionsBo;
import com.ebgolden.domain.actionservice.bll.bo.CharacterAndPlayerBo;

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