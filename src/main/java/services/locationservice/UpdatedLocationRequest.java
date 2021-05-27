package services.locationservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Location;
import commonobjects.Player;

@Builder
@Data
public class UpdatedLocationRequest {
    Location location;
    Player player;
}