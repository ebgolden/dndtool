package domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Action;
import common.Character;
import common.Die;
import common.Player;

@Builder
@Value
public class ActionAndDiceAndCharacterAndPlayerBo {
    Action action;
    Die[] dice;
    Character character;
    Player player;
}