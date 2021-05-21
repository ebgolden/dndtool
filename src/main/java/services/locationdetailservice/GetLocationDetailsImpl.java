package services.locationdetailservice;

import com.google.inject.Inject;
import services.locationdetailservice.bll.LocationDetailBusinessLogic;
import services.locationdetailservice.bll.LocationDetailBusinessLogicConverter;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;

public class GetLocationDetailsImpl implements GetLocationDetails {
    @Inject
    private LocationDetailBusinessLogicConverter locationDetailBusinessLogicConverter;
    @Inject
    private LocationDetailBusinessLogic locationDetailBusinessLogic;

    public LocationDetailsResponse getLocationDetailsResponse(LocationDetailsRequest locationDetailsRequest) {
        LocationAndPlayerBo locationAndPlayerBo = locationDetailBusinessLogicConverter.getLocationAndPlayerBoFromLocationDetailsRequest(locationDetailsRequest);
        LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo = locationDetailBusinessLogic.getLocationDetailsAndVisibilityBo(locationAndPlayerBo);
        return locationDetailBusinessLogicConverter.getLocationDetailsResponseFromLocationDetailsAndVisibilityBo(locationDetailsAndVisibilityBo);
    }
}