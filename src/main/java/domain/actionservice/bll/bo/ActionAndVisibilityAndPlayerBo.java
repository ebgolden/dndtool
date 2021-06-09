package domain.actionservice.bll.bo;

import common.Action;
import common.Player;
import common.Visibility;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
public class ActionAndVisibilityAndPlayerBo {
    Action action;
    Map<String, Visibility> visibilityMap;
    Player player;
}