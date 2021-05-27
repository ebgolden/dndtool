package services.resultservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Player;
import commonobjects.Result;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfResultDetailsRequest {
    Result result;
    Map<String, Visibility> visibilityMap;
    Player player;
}