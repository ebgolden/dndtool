package services.locationdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Location;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class LocationDetailsAndVisibilityBo {
    Location location;
    Map<String, Visibility> visibilityMap;
}