package services.resultdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}