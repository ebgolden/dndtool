package services.locationdetailservice.bll;

import objects.Location;
import objects.Player;
import services.locationdetailservice.LocationDetailsRequest;
import services.locationdetailservice.LocationDetailsResponse;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
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

    public LocationDetailsResponse getLocationDetailsResponseFromLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo) {
        Location location = locationDetailsAndVisibilityBo.getLocation();
        return LocationDetailsResponse
                .builder()
                .location(location)
                .build();
    }
}