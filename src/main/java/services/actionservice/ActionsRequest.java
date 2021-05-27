package services.actionservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.Player;

@Builder
@Value
public class ActionsRequest {
    Character character;
    Player player;
}