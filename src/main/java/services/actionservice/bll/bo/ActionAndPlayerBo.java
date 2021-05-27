package services.actionservice.bll.bo;

import commonobjects.Action;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ActionAndPlayerBo {
    Action action;
    Player player;
}