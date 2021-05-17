package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;
import objects.World;

@Builder
@Value
public class WorldDetailsResponse {
    World world;
}