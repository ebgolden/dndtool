package services.worlddetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.DungeonMaster;
import objects.Visibility;
import objects.World;
import java.util.Map;

@Builder
@Value
public class WorldDetailsAndVisibilityAndDungeonMasterBo {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}