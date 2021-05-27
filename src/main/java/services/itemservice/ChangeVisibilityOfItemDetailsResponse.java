package services.itemservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfItemDetailsResponse {
    Map<String, Visibility> visibilityMap;
}