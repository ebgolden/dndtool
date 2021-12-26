package com.ebgolden.domain.locationservice;

import com.google.inject.Inject;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogic;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogicConverter;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndPlayerBo;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndVisibilityBo;

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