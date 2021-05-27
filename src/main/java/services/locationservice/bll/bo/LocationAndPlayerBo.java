package services.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Location;
import commonobjects.Player;

@Builder
@Data
public class LocationAndPlayerBo {
    Location location;
    Player player;
}