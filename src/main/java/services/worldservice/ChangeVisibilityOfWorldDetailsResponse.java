package services.worldservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfWorldDetailsResponse {
    Map<String, Visibility> visibilityMap;
}