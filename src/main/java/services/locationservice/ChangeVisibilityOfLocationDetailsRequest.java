package services.locationservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.Location;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfLocationDetailsRequest {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}