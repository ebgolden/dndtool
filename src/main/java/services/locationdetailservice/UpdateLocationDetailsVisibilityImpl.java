package services.locationdetailservice;

import com.google.inject.Inject;
import services.locationdetailservice.bll.LocationDetailBusinessLogic;
import services.locationdetailservice.bll.LocationDetailBusinessLogicConverter;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;

public class UpdateLocationDetailsVisibilityImpl implements UpdateLocationDetailsVisibility {
    @Inject
    private LocationDetailBusinessLogicConverter locationDetailBusinessLogicConverter;
    @Inject
    private LocationDetailBusinessLogic locationDetailBusinessLogic;

    public LocationDetailsVisibilityResponse getLocationDetailsVisibilityResponse(LocationDetailsVisibilityRequest locationDetailsVisibilityRequest) {
        LocationDetailsAndVisibilityAndPlayerBo locationDetailsAndVisibilityAndPlayerBo = locationDetailBusinessLogicConverter.getLocationDetailsAndVisibilityAndPlayerBoFromLocationDetailsVisibilityRequest(locationDetailsVisibilityRequest);
        LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo = locationDetailBusinessLogic.getLocationDetailsAndVisibilityBo(locationDetailsAndVisibilityAndPlayerBo);
        return locationDetailBusinessLogicConverter.getLocationDetailsVisibilityResponseFromLocationDetailsAndVisibilityBo(locationDetailsAndVisibilityBo);
    }
}