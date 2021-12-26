package com.ebgolden.domain.worldservice.bll;

import com.ebgolden.domain.worldservice.bll.bo.WorldAndPlayerBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;

public interface WorldBusinessLogic {
    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo);

    WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo);
}