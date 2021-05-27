package services.locationservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Location;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class LocationAndVisibilityBo {
    Location location;
    Map<String, Visibility> visibilityMap;
}