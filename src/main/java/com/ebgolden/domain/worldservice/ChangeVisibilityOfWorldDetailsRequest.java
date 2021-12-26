package com.ebgolden.domain.worldservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Visibility;
import com.ebgolden.common.World;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfWorldDetailsRequest {
    World world;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}