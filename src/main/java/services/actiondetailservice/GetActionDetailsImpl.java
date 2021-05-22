package services.actiondetailservice;

import com.google.inject.Inject;
import services.actiondetailservice.bll.ActionDetailBusinessLogic;
import services.actiondetailservice.bll.ActionDetailBusinessLogicConverter;
import services.actiondetailservice.bll.bo.ActionAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityBo;

public class GetActionDetailsImpl implements GetActionDetails {
    @Inject
    private ActionDetailBusinessLogicConverter actionDetailBusinessLogicConverter;
    @Inject
    private ActionDetailBusinessLogic actionDetailBusinessLogic;

    public ActionDetailsResponse getActionDetailsResponse(ActionDetailsRequest actionDetailsRequest) {
        ActionAndPlayerBo actionAndPlayerBo = actionDetailBusinessLogicConverter.getActionAndPlayerBoFromActionDetailsRequest(actionDetailsRequest);
        ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo = actionDetailBusinessLogic.getActionDetailsAndVisibilityBo(actionAndPlayerBo);
        return actionDetailBusinessLogicConverter.getActionDetailsResponseFromActionDetailsAndVisibilityBo(actionDetailsAndVisibilityBo);
    }
}