package com.ebgolden.domain.locationservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Location;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfLocationDetailsRequest {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}