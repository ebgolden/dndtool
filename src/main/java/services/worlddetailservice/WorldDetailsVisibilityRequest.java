package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.Visibility;
import objects.World;
import java.util.Map;

@Builder
@Value
public class WorldDetailsVisibilityRequest {
    World world;
    Map<String, Visibility> visibilityMap;
    Player player;
}