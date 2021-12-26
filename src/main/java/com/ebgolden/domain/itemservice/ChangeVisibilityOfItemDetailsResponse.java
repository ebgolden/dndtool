package domain.itemservice;

import lombok.Builder;
import lombok.Data;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfItemDetailsResponse {
    Map<String, Visibility> visibilityMap;
}