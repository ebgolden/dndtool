package services.actionservice.bll.bo;

import commonobjects.Action;
import commonobjects.Player;
import commonobjects.Visibility;
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