package services.locationservice.bll;

import services.locationservice.bll.bo.LocationAndPlayerBo;
import services.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import services.locationservice.bll.bo.LocationAndVisibilityBo;

public interface LocationBusinessLogic {
    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo);

    LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo);
}