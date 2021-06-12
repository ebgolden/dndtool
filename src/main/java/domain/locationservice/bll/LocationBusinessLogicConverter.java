package domain.locationservice.bll;

import domain.locationservice.UpdatedLocationRequest;
import domain.locationservice.UpdatedLocationResponse;
import domain.locationservice.ChangeVisibilityOfLocationDetailsRequest;
import domain.locationservice.ChangeVisibilityOfLocationDetailsResponse;
import domain.locationservice.bll.bo.LocationAndPlayerBo;
import domain.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;

public interface LocationBusinessLogicConverter {
    LocationAndPlayerBo getLocationAndPlayerBoFromUpdatedLocationRequest(UpdatedLocationRequest updatedLocationRequest);

    LocationAndVisibilityAndDungeonMasterBo getLocationAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfLocationDetailsRequest(ChangeVisibilityOfLocationDetailsRequest changeVisibilityOfUpdatedLocationRequest);

    UpdatedLocationResponse getUpdatedLocationResponseFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo);

    ChangeVisibilityOfLocationDetailsResponse getChangeVisibilityOfLocationDetailsResponseFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo);

    LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityAndDungeonMasterBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo);
}