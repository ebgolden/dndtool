package services.actionservice;

import commonobjects.Action;
import commonobjects.Player;
import commonobjects.Visibility;
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