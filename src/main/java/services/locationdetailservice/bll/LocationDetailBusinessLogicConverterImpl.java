package services.locationdetailservice.bll;

import objects.Location;
import objects.Player;
import services.locationdetailservice.LocationDetailsRequest;
import services.locationdetailservice.LocationDetailsResponse;
import services.locationdetailservice.LocationDetailsVisibilityRequest;
import services.locationdetailservice.LocationDetailsVisibilityResponse;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;

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
        String visibilityJson = locationDetailsVisibilityRequest.getVisibilityJson();
        Player player = locationDetailsVisibilityRequest.getPlayer();
        return LocationDetailsAndVisibilityAndPlayerBo
                .builder()
                .location(location)
                .visibilityJson(visibilityJson)
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
        String visibilityJson = locationDetailsAndVisibilityBo.getVisibilityJson();
        return LocationDetailsVisibilityResponse
                .builder()
                .visibilityJson(visibilityJson)
                .build();
    }

    public LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityAndPlayerBo(LocationDetailsAndVisibilityAndPlayerBo locationDetailsAndVisibilityAndPlayerBo) {
        Location location = locationDetailsAndVisibilityAndPlayerBo.getLocation();
        String visibilityJson = locationDetailsAndVisibilityAndPlayerBo.getVisibilityJson();
        return LocationDetailsAndVisibilityBo
                .builder()
                .location(location)
                .visibilityJson(visibilityJson)
                .build();
    }
}