package domain.worldservice;

import com.google.inject.Inject;
import domain.worldservice.bll.WorldBusinessLogic;
import domain.worldservice.bll.WorldBusinessLogicConverter;
import domain.worldservice.bll.bo.WorldAndPlayerBo;
import domain.worldservice.bll.bo.WorldAndVisibilityBo;

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