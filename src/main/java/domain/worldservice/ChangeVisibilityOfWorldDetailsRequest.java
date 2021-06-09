package domain.worldservice;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.Visibility;
import common.World;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfWorldDetailsRequest {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}