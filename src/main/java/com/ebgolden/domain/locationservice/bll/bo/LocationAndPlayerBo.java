package domain.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Location;
import common.Player;

@Builder
@Data
public class LocationAndPlayerBo {
    Location location;
    Player player;
}