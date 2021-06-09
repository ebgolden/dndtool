package domain.locationservice;

import com.google.inject.Inject;
import domain.locationservice.bll.LocationBusinessLogic;
import domain.locationservice.bll.LocationBusinessLogicConverter;
import domain.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;

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