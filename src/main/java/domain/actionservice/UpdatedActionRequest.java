package domain.actionservice;

import common.Action;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionRequest {
    Action action;
    Player player;
}