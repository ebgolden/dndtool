package services.worlddetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.World;

@Builder
@Value
public class WorldDetailsAndVisibilityBo {
    World world;
    String visibilityJson;
}