package services.locationdetailservice.bll;

import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;

public interface LocationDetailBusinessLogic {
    LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo);
}