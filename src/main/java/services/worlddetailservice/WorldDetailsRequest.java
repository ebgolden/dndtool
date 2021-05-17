package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.World;

@Builder
@Value
public class WorldDetailsRequest {
    World world;
    Player player;
}