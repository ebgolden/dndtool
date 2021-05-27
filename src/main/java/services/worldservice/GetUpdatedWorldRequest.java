package services.worldservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.World;

@Builder
@Value
public class GetUpdatedWorldRequest {
    World world;
    Player player;
}