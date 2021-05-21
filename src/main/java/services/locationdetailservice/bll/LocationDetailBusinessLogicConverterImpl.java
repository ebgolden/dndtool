package services.locationdetailservice.bll;

import objects.Location;
import objects.Player;
import objects.Visibility;
import services.locationdetailservice.LocationDetailsRequest;
import services.locationdetailservice.LocationDetailsResponse;
import services.locationdetailservice.LocationDetailsVisibilityRequest;
import services.locationdetailservice.LocationDetailsVisibilityResponse;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;
import java.util.Map;

public class LocationDetailBusinessLogicConverterImpl implements LocationDetailBusinessLogicConverter {
    public LocationAndPlayerBo getLocationAndPlayerBoFromLocationDetailsRequest(LocationDetailsRequest locationDetailsRequest) {
        Location location = locationDetailsRequest.getLocation();
        Player player = locationDetailsRequest.getPlayer();
        return LocationAndPlayerBo
                .builder()
                .location(location)
                .player(player)
                .build();
    }

    public LocationDetailsAndVisibilityAndPlayerBo getLocationDetailsAndVisibilityAndPlayerBoFromLocationDetailsVisibilityRequest(LocationDetailsVisibilityRequest locationDetailsVisibilityRequest) {
        Location location = locationDetailsVisibilityRequest.getLocation();
        Map<String, Visibility> visibilityMap = locationDetailsVisibilityRequest.getVisibilityMap();
        Player player = locationDetailsVisibilityRequest.getPlayer();
        return LocationDetailsAndVisibilityAndPlayerBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public LocationDetailsResponse getLocationDetailsResponseFromLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo) {
        Location location = locationDetailsAndVisibilityBo.getLocation();
        return LocationDetailsResponse
                .builder()
                .location(location)
                .build();
    }

    public LocationDetailsVisibilityResponse getLocationDetailsVisibilityResponseFromLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = locationDetailsAndVisibilityBo.getVisibilityMap();
        return LocationDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityAndPlayerBo(LocationDetailsAndVisibilityAndPlayerBo locationDetailsAndVisibilityAndPlayerBo) {
        Location location = locationDetailsAndVisibilityAndPlayerBo.getLocation();
        Map<String, Visibility> visibilityMap = locationDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return LocationDetailsAndVisibilityBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .build();
    }
}