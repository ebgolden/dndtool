package services.locationservice;

import com.google.inject.Inject;
import services.locationservice.bll.LocationBusinessLogic;
import services.locationservice.bll.LocationBusinessLogicConverter;
import services.locationservice.bll.bo.LocationAndPlayerBo;
import services.locationservice.bll.bo.LocationAndVisibilityBo;

public class GetUpdatedLocationImpl implements GetUpdatedLocation {
    @Inject
    private LocationBusinessLogicConverter locationBusinessLogicConverter;
    @Inject
    private LocationBusinessLogic locationBusinessLogic;

    public UpdatedLocationResponse getUpdatedLocationResponse(UpdatedLocationRequest updatedLocationRequest) {
        LocationAndPlayerBo locationAndPlayerBo = locationBusinessLogicConverter.getLocationAndPlayerBoFromUpdatedLocationRequest(updatedLocationRequest);
        LocationAndVisibilityBo locationAndVisibilityBo = locationBusinessLogic.getLocationAndVisibilityBo(locationAndPlayerBo);
        return locationBusinessLogicConverter.getUpdatedLocationResponseFromLocationAndVisibilityBo(locationAndVisibilityBo);
    }
}