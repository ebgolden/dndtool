package com.ebgolden.domain.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Location;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class LocationAndVisibilityAndDungeonMasterBo {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}