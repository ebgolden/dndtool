package domain.actionservice.bll.bo;

import common.Action;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ActionAndPlayerBo {
    Action action;
    Player player;
}