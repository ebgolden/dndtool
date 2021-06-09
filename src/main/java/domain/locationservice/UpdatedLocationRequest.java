package domain.locationservice;

import lombok.Builder;
import lombok.Data;
import common.Location;
import common.Player;

@Builder
@Data
public class UpdatedLocationRequest {
    Location location;
    Player player;
}