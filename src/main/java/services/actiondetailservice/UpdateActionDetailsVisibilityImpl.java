package services.actiondetailservice;

import com.google.inject.Inject;
import services.actiondetailservice.bll.ActionDetailBusinessLogic;
import services.actiondetailservice.bll.ActionDetailBusinessLogicConverter;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityBo;

public class UpdateActionDetailsVisibilityImpl implements UpdateActionDetailsVisibility {
    @Inject
    private ActionDetailBusinessLogicConverter actionDetailBusinessLogicConverter;
    @Inject
    private ActionDetailBusinessLogic actionDetailBusinessLogic;

    public ActionDetailsVisibilityResponse getActionDetailsVisibilityResponse(ActionDetailsVisibilityRequest actionDetailsVisibilityRequest) {
        ActionDetailsAndVisibilityAndPlayerBo actionDetailsAndVisibilityAndPlayerBo = actionDetailBusinessLogicConverter.getActionDetailsAndVisibilityAndPlayerBoFromActionDetailsVisibilityRequest(actionDetailsVisibilityRequest);
        ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo = actionDetailBusinessLogic.getActionDetailsAndVisibilityBo(actionDetailsAndVisibilityAndPlayerBo);
        return actionDetailBusinessLogicConverter.getActionDetailsVisibilityResponseFromActionDetailsAndVisibilityBo(actionDetailsAndVisibilityBo);
    }
}