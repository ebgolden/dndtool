package services.worldservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.Visibility;
import commonobjects.World;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfWorldDetailsRequest {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}