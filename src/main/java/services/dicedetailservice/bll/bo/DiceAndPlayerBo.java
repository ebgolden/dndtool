package services.dicedetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Die;
import objects.Player;

@Builder
@Value
public class DiceAndPlayerBo {
    Die[] dice;
    Player player;
}