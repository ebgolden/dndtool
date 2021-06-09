package domain.worldservice;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.World;

@Builder
@Value
public class GetUpdatedWorldRequest {
    World world;
    Player player;
}