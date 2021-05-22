package services.actiondetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Player;

@Builder
@Value
public class ActionDetailsRequest {
    Action action;
    Player player;
}