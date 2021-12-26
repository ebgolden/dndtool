package domain.resultservice;

import lombok.Builder;
import lombok.Value;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfResultDetailsResponse {
    Map<String, Visibility> visibilityMap;
}