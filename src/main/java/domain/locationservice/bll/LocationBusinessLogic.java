package domain.locationservice.bll;

import domain.locationservice.bll.bo.LocationAndPlayerBo;
import domain.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;

public interface LocationBusinessLogic {
    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo);
}