package domain.locationservice.bll;

import common.DungeonMaster;
import common.Location;
import common.Player;
import common.Visibility;
import domain.locationservice.ChangeVisibilityOfLocationDetailsRequest;
import domain.locationservice.ChangeVisibilityOfLocationDetailsResponse;
import domain.locationservice.UpdatedLocationRequest;
import domain.locationservice.UpdatedLocationResponse;
import domain.locationservice.bll.bo.LocationAndPlayerBo;
import domain.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import domain.locationservice.bll.bo.LocationAndVisibilityBo;
import java.util.Map;

public class LocationBusinessLogicConverterImpl implements LocationBusinessLogicConverter {
    public LocationAndPlayerBo getLocationAndPlayerBoFromUpdatedLocationRequest(UpdatedLocationRequest updatedLocationRequest) {
        Location location = updatedLocationRequest.getLocation();
        Player player = updatedLocationRequest.getPlayer();
        return LocationAndPlayerBo
                .builder()
                .location(location)
                .player(player)
                .build();
    }

    public LocationAndVisibilityAndDungeonMasterBo getLocationAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfLocationDetailsRequest(ChangeVisibilityOfLocationDetailsRequest changeVisibilityOfUpdatedLocationRequest) {
        Location location = changeVisibilityOfUpdatedLocationRequest.getLocation();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedLocationRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = changeVisibilityOfUpdatedLocationRequest.getDungeonMaster();
        return LocationAndVisibilityAndDungeonMasterBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public UpdatedLocationResponse getUpdatedLocationResponseFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo) {
        Location location = locationAndVisibilityBo.getLocation();
        return UpdatedLocationResponse
                .builder()
                .location(location)
                .build();
    }

    public ChangeVisibilityOfLocationDetailsResponse getChangeVisibilityOfLocationDetailsResponseFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = locationAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfLocationDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityAndDungeonMasterBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo) {
        Location location = locationAndVisibilityAndDungeonMasterBo.getLocation();
        Map<String, Visibility> visibilityMap = locationAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        return LocationAndVisibilityBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .build();
    }
}