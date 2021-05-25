package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;
import objects.DungeonMaster;
import objects.Visibility;
import objects.World;
import java.util.Map;

@Builder
@Value
public class WorldDetailsVisibilityRequest {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}