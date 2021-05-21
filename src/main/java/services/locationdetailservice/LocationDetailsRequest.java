package services.locationdetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Location;
import objects.Player;

@Builder
@Data
public class LocationDetailsRequest {
    Location location;
    Player player;
}