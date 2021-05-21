package services.locationdetailservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LocationDetailsVisibilityResponse {
    String visibilityJson;
}