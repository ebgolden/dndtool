package domain.actionservice;

import common.Action;
import common.Player;
import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfActionDetailsRequest {
    Action action;
    Map<String, Visibility> visibilityMap;
    Player player;
}