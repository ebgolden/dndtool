package com.ebgolden.domain.locationservice.bll;

import com.ebgolden.domain.locationservice.bll.bo.LocationAndPlayerBo;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndVisibilityBo;

public interface LocationBusinessLogic {
    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo);
}