package domain.locationservice;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.Location;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfLocationDetailsRequest {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}