package services.actiondetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Player;

@Builder
@Value
public class ActionAndPlayerBo {
    Action action;
    Player player;
}