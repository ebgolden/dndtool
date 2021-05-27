package services.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.World;

@Builder
@Value
public class WorldAndPlayerBo {
    World world;
    Player player;
}