package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionAndVisibilityAndPlayerBo;
import domain.actionservice.bll.bo.ActionAndVisibilityBo;

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