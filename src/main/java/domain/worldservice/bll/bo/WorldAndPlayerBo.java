package domain.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.World;

@Builder
@Value
public class WorldAndPlayerBo {
    World world;
    Player player;
}