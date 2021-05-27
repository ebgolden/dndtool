package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionAndVisibilityAndPlayerBo;
import services.actionservice.bll.bo.ActionAndVisibilityBo;

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