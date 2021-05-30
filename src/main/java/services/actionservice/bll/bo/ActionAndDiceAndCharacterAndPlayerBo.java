package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Action;
import commonobjects.Character;
import commonobjects.Die;
import commonobjects.Player;

@Builder
@Value
public class ActionAndDiceAndCharacterAndPlayerBo {
    Action action;
    Die[] dice;
    Character character;
    Player player;
}