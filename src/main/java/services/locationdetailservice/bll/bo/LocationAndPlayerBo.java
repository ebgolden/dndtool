package services.locationdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Location;
import objects.Player;

@Builder
@Data
public class LocationAndPlayerBo {
    Location location;
    Player player;
}