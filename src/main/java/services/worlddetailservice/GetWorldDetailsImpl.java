package services.worlddetailservice;

import com.google.inject.Inject;
import services.worlddetailservice.bll.WorldDetailBusinessLogic;
import services.worlddetailservice.bll.WorldDetailBusinessLogicConverter;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public class GetWorldDetailsImpl implements GetWorldDetails {
    @Inject
    private WorldDetailBusinessLogicConverter worldDetailBusinessLogicConverter;
    @Inject
    private WorldDetailBusinessLogic worldDetailBusinessLogic;

    public WorldDetailsResponse getWorldDetailsResponse(WorldDetailsRequest worldDetailsRequest) {
        WorldAndPlayerBo worldAndPlayerBo = worldDetailBusinessLogicConverter.getWorldAndPlayerBoFromWorldDetailsRequest(worldDetailsRequest);
        WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo = worldDetailBusinessLogic.getWorldDetailsAndVisibilityBo(worldAndPlayerBo);
        return worldDetailBusinessLogicConverter.getWorldDetailsResponseFromWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityBo);
    }
}