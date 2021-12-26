package domain.resultservice;

import lombok.Builder;
import lombok.Value;
import common.Player;
import common.Result;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfResultDetailsRequest {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}