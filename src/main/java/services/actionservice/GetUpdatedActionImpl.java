package services.actionservice;

import com.google.inject.Inject;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.bo.ActionAndPlayerBo;
import services.actionservice.bll.bo.ActionAndVisibilityBo;

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