package domain.actionservice;

import com.google.inject.Inject;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.bo.ActionAndPlayerBo;
import domain.actionservice.bll.bo.ActionAndVisibilityBo;

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