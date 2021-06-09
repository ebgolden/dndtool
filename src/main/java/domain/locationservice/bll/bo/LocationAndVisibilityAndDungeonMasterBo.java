package domain.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.DungeonMaster;
import common.Location;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class LocationAndVisibilityAndDungeonMasterBo {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}