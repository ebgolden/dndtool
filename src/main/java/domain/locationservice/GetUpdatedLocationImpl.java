package domain.locationservice;

import com.google.inject.Inject;
import domain.locationservice.bll.LocationBusinessLogic;
import domain.locationservice.bll.LocationBusinessLogicConverter;
import domain.locationservice.bll.bo.LocationAndPlayerBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;

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