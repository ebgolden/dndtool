package services.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.Visibility;
import commonobjects.World;
import java.util.Map;

@Builder
@Value
public class WorldAndVisibilityAndDungeonMasterBo {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}