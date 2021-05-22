package services.actiondetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class ActionDetailsAndVisibilityAndPlayerBo {
    Action action;
    Map<String, Visibility> visibilityMap;
    Player player;
}