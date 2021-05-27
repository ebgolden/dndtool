package services.worldservice;

import com.google.inject.Inject;
import services.worldservice.bll.WorldBusinessLogic;
import services.worldservice.bll.WorldBusinessLogicConverter;
import services.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;

public class ChangeVisibilityOfWorldDetailsImpl implements ChangeVisibilityOfWorldDetails {
    @Inject
    private WorldBusinessLogicConverter worldBusinessLogicConverter;
    @Inject
    private WorldBusinessLogic worldBusinessLogic;

    public ChangeVisibilityOfWorldDetailsResponse getChangeVisibilityOfWorldDetailsResponse(ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest) {
        WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo = worldBusinessLogicConverter.getWorldAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfWorldDetailsRequest(changeVisibilityOfGetUpdatedWorldRequest);
        WorldAndVisibilityBo worldAndVisibilityBo = worldBusinessLogic.getWorldAndVisibilityBo(worldAndVisibilityAndDungeonMasterBo);
        return worldBusinessLogicConverter.getChangeVisibilityOfWorldDetailsResponseFromWorldAndVisibilityBo(worldAndVisibilityBo);
    }
}