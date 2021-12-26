package com.ebgolden.domain.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Player;
import com.ebgolden.common.World;

@Builder
@Value
public class WorldAndPlayerBo {
    World world;
    Player player;
}