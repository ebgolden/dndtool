package services.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Die;
import commonobjects.Player;

@Builder
@Value
public class DiceAndPlayerBo {
    Die[] dice;
    Player player;
}