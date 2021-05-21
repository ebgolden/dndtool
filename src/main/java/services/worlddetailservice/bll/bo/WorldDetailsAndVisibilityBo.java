package services.worlddetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import objects.World;
import java.util.Map;

@Builder
@Value
public class WorldDetailsAndVisibilityBo {
    World world;
    Map<String, Visibility> visibilityMap;
}