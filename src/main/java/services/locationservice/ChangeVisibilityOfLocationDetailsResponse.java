package services.locationservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfLocationDetailsResponse {
    Map<String, Visibility> visibilityMap;
}