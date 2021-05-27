package services.worldservice;

import com.google.inject.Inject;
import services.worldservice.bll.WorldBusinessLogic;
import services.worldservice.bll.WorldBusinessLogicConverter;
import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;

public class GetUpdatedWorldDetailsImpl implements GetUpdatedWorldDetails {
    @Inject
    private WorldBusinessLogicConverter worldBusinessLogicConverter;
    @Inject
    private WorldBusinessLogic worldBusinessLogic;

    public GetUpdatedWorldResponse getGetUpdatedWorldResponse(GetUpdatedWorldRequest getUpdatedWorldRequest) {
        WorldAndPlayerBo worldAndPlayerBo = worldBusinessLogicConverter.getWorldAndPlayerBoFromGetUpdatedWorldRequest(getUpdatedWorldRequest);
        WorldAndVisibilityBo worldAndVisibilityBo = worldBusinessLogic.getWorldAndVisibilityBo(worldAndPlayerBo);
        return worldBusinessLogicConverter.getGetUpdatedWorldResponseFromWorldAndVisibilityBo(worldAndVisibilityBo);
    }
}