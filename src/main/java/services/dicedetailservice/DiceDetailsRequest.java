package services.dicedetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Die;
import objects.Player;

@Builder
@Value
public class DiceDetailsRequest {
    Die[] dice;
    Player player;
}