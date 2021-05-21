package services.locationdetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Location;

@Builder
@Data
public class LocationDetailsAndVisibilityBo {
    Location location;
    String visibilityJson;
}