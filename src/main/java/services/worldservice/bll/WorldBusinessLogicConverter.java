package services.worldservice.bll;

import services.worldservice.ChangeVisibilityOfWorldDetailsRequest;
import services.worldservice.ChangeVisibilityOfWorldDetailsResponse;
import services.worldservice.GetUpdatedWorldRequest;
import services.worldservice.GetUpdatedWorldResponse;
import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;

public interface WorldBusinessLogicConverter {
    WorldAndPlayerBo getWorldAndPlayerBoFromGetUpdatedWorldRequest(GetUpdatedWorldRequest getUpdatedWorldRequest);

    GetUpdatedWorldResponse getGetUpdatedWorldResponseFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo);

    ChangeVisibilityOfWorldDetailsResponse getChangeVisibilityOfWorldDetailsResponseFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo);

    WorldAndVisibilityAndDungeonMasterBo getWorldAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfWorldDetailsRequest(ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest);

    WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityAndDungeonMasterBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo);
}