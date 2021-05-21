package services.worlddetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.Visibility;
import objects.World;
import java.util.Map;

@Builder
@Value
public class WorldDetailsAndVisibilityAndPlayerBo {
    World world;
    Map<String, Visibility> visibilityMap;
    Player player;
}