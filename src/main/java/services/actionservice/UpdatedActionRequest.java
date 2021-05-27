package services.actionservice;

import commonobjects.Action;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionRequest {
    Action action;
    Player player;
}