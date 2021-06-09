package domain.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Die;
import common.Player;

@Builder
@Value
public class DiceAndPlayerBo {
    Die[] dice;
    Player player;
}