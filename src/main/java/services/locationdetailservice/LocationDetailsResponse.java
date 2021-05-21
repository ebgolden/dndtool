package services.locationdetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Location;

@Builder
@Data
public class LocationDetailsResponse {
    Location location;
}