package services.locationdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class LocationDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}