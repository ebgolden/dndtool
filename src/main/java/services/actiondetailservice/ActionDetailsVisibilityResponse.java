package services.actiondetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ActionDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}