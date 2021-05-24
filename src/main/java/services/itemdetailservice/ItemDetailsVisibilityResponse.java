package services.itemdetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class ItemDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}