package services.locationdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Location;
import objects.Player;

@Builder
@Data
public class LocationDetailsAndVisibilityAndPlayerBo {
    Location location;
    String visibilityJson;
    Player player;
}