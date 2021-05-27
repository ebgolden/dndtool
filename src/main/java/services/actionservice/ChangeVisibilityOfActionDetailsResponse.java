package services.actionservice;

import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfActionDetailsResponse {
    Map<String, Visibility> visibilityMap;
}