package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Action;
import objects.Character;
import objects.Player;

@Builder
@Value
public class ActionAndDiceRollsAndCharacterAndPlayerBo {
    Action action;
    int[] diceRolls;
    Character character;
    Player player;
}