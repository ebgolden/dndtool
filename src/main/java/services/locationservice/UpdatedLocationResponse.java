package services.locationservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Location;

@Builder
@Data
public class UpdatedLocationResponse {
    Location location;
}