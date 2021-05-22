package services.actiondetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ActionDetailsVisibilityRequest {
    Action action;
    Map<String, Visibility> visibilityMap;
    Player player;
}