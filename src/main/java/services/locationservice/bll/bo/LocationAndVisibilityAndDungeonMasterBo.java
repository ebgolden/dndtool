package services.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.DungeonMaster;
import commonobjects.Location;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class LocationAndVisibilityAndDungeonMasterBo {
    Location location;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}