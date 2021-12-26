package domain.locationservice;

import lombok.Builder;
import lombok.Data;
import common.Location;

@Builder
@Data
public class UpdatedLocationResponse {
    Location location;
}