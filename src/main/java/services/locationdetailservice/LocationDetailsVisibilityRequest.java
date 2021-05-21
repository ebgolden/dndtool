package services.locationdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Location;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class LocationDetailsVisibilityRequest {
    Location location;
    Map<String, Visibility> visibilityMap;
    Player player;
}