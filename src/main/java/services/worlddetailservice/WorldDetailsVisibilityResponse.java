package services.worlddetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class WorldDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}