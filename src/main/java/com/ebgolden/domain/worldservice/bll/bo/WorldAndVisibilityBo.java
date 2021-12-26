package domain.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Visibility;
import common.World;
import java.util.Map;

@Builder
@Value
public class WorldAndVisibilityBo {
    World world;
    Map<String, Visibility> visibilityMap;
}