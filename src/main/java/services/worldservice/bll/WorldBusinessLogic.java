package services.worldservice.bll;

import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;

public interface WorldBusinessLogic {
    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo);
}