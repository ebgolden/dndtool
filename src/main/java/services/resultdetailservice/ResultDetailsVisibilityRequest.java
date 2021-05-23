package services.resultdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Player;
import objects.Result;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ResultDetailsVisibilityRequest {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}