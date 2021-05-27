package services.locationservice;

import com.google.inject.Inject;
import services.locationservice.bll.LocationBusinessLogic;
import services.locationservice.bll.LocationBusinessLogicConverter;
import services.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import services.locationservice.bll.bo.LocationAndVisibilityBo;

public class ChangeVisibilityOfLocationDetailsImpl implements ChangeVisibilityOfLocationDetails {
    @Inject
    private LocationBusinessLogicConverter locationBusinessLogicConverter;
    @Inject
    private LocationBusinessLogic locationBusinessLogic;

    public ChangeVisibilityOfLocationDetailsResponse getChangeVisibilityOfLocationDetailsResponse(ChangeVisibilityOfLocationDetailsRequest changeVisibilityOfUpdatedLocationRequest) {
        LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo = locationBusinessLogicConverter.getLocationAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfLocationDetailsRequest(changeVisibilityOfUpdatedLocationRequest);
        LocationAndVisibilityBo locationAndVisibilityBo = locationBusinessLogic.getLocationAndVisibilityBo(locationAndVisibilityAndDungeonMasterBo);
        return locationBusinessLogicConverter.getChangeVisibilityOfLocationDetailsResponseFromLocationAndVisibilityBo(locationAndVisibilityBo);
    }
}