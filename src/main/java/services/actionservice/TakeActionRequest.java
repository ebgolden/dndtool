package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Character;
import objects.Player;

@Builder
@Value
public class TakeActionRequest {
    Action action;
    int[] diceRolls;
    Character character;
    Player player;
}