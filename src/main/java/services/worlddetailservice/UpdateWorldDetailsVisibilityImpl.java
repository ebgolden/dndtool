package services.worlddetailservice;

import com.google.inject.Inject;
import services.worlddetailservice.bll.WorldDetailBusinessLogic;
import services.worlddetailservice.bll.WorldDetailBusinessLogicConverter;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public class UpdateWorldDetailsVisibilityImpl implements UpdateWorldDetailsVisibility {
    @Inject
    private WorldDetailBusinessLogicConverter worldDetailBusinessLogicConverter;
    @Inject
    private WorldDetailBusinessLogic worldDetailBusinessLogic;

    public WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest) {
        WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo = worldDetailBusinessLogicConverter.getWorldDetailsAndVisibilityAndPlayerBoFromWorldDetailsVisibilityRequest(worldDetailsVisibilityRequest);
        WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo = worldDetailBusinessLogic.getWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityAndPlayerBo);
        return worldDetailBusinessLogicConverter.getWorldDetailsVisibilityResponseFromWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityBo);
    }
}