package domain.worldservice.bll;

import domain.worldservice.bll.bo.WorldAndPlayerBo;
import domain.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import domain.worldservice.bll.bo.WorldAndVisibilityBo;

public interface WorldBusinessLogic {
    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo);
}