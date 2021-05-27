package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Character;
import objects.Die;
import objects.Player;

@Builder
@Value
public class TakeActionRequest {
    Action action;
    Die[] dice;
    Character character;
    Player player;
}