package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;

@Builder
@Value
public class ActionsRequest {
    Character character;
    Player player;
}