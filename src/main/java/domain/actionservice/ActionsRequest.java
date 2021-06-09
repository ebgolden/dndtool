package domain.actionservice;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.Player;

@Builder
@Value
public class ActionsRequest {
    Character character;
    Player player;
}