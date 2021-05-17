package services.worlddetailservice.bll;

import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;

public interface WorldDetailBusinessLogic {
    WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo);
}