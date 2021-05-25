package services.worlddetailservice;

import com.google.inject.Inject;
import services.worlddetailservice.bll.WorldDetailBusinessLogic;
import services.worlddetailservice.bll.WorldDetailBusinessLogicConverter;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityAndDungeonMasterBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public class UpdateWorldDetailsVisibilityImpl implements UpdateWorldDetailsVisibility {
    @Inject
    private WorldDetailBusinessLogicConverter worldDetailBusinessLogicConverter;
    @Inject
    private WorldDetailBusinessLogic worldDetailBusinessLogic;

    public WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest) {
        WorldDetailsAndVisibilityAndDungeonMasterBo worldDetailsAndVisibilityAndDungeonMasterBo = worldDetailBusinessLogicConverter.getWorldDetailsAndVisibilityAndDungeonMasterBoFromWorldDetailsVisibilityRequest(worldDetailsVisibilityRequest);
        WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo = worldDetailBusinessLogic.getWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityAndDungeonMasterBo);
        return worldDetailBusinessLogicConverter.getWorldDetailsVisibilityResponseFromWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityBo);
    }
}