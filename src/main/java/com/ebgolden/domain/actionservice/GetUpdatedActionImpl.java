package com.ebgolden.domain.actionservice;

import com.google.inject.Inject;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogic;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverter;
import com.ebgolden.domain.actionservice.bll.bo.ActionAndPlayerBo;
import com.ebgolden.domain.actionservice.bll.bo.ActionAndVisibilityBo;

public class GetUpdatedActionImpl implements GetUpdatedAction {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public UpdatedActionResponse getUpdatedActionResponse(UpdatedActionRequest updatedActionRequest) {
        ActionAndPlayerBo actionAndPlayerBo = actionBusinessLogicConverter.getActionAndPlayerBoFromUpdatedActionRequest(updatedActionRequest);
        ActionAndVisibilityBo actionAndVisibilityBo = actionBusinessLogic.getActionAndVisibilityBo(actionAndPlayerBo);
        return actionBusinessLogicConverter.getUpdatedActionResponseFromActionAndVisibilityBo(actionAndVisibilityBo);
    }
}