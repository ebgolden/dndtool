package services.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Visibility;
import commonobjects.World;
import java.util.Map;

@Builder
@Value
public class WorldAndVisibilityBo {
    World world;
    Map<String, Visibility> visibilityMap;
}