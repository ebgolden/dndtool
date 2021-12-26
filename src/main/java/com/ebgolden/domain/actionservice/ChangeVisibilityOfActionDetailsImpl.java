package com.ebgolden.domain.actionservice;

import com.google.inject.Inject;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogic;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverter;
import com.ebgolden.domain.actionservice.bll.bo.ActionAndVisibilityAndPlayerBo;
import com.ebgolden.domain.actionservice.bll.bo.ActionAndVisibilityBo;

public class ChangeVisibilityOfActionDetailsImpl implements ChangeVisibilityOfActionDetails {
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;
    @Inject
    private ActionBusinessLogic actionBusinessLogic;

    public ChangeVisibilityOfActionDetailsResponse getChangeVisibilityOfActionDetailsResponse(ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest) {
        ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo = actionBusinessLogicConverter.getActionAndVisibilityAndPlayerBoFromChangeVisibilityOfActionDetailsRequest(changeVisibilityOfActionDetailsRequest);
        ActionAndVisibilityBo actionAndVisibilityBo = actionBusinessLogic.getActionAndVisibilityBo(actionAndVisibilityAndPlayerBo);
        return actionBusinessLogicConverter.getChangeVisibilityOfActionDetailsResponseFromActionAndVisibilityBo(actionAndVisibilityBo);
    }
}