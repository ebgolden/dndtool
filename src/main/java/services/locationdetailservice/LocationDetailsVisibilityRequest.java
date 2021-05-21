package services.locationdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Location;
import objects.Player;

@Builder
@Value
public class LocationDetailsVisibilityRequest {
    Location location;
    String visibilityJson;
    Player player;
}