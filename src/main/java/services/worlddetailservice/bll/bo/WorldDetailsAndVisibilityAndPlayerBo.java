package services.worlddetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.World;

@Builder
@Value
public class WorldDetailsAndVisibilityAndPlayerBo {
    World world;
    String visibilityJson;
    Player player;
}