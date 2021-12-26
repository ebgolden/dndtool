package com.ebgolden.domain.worldservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Visibility;
import com.ebgolden.common.World;
import java.util.Map;

@Builder
@Value
public class WorldAndVisibilityBo {
    World world;
    Map<String, Visibility> visibilityMap;
}