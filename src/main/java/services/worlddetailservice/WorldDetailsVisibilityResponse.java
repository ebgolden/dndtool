package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WorldDetailsVisibilityResponse {
    String visibilityJson;
}