package domain.diceservice;

import lombok.Builder;
import lombok.Value;
import common.Die;
import common.Player;

@Builder
@Value
public class UpdatedDiceRequest {
    Die[] dice;
    Player player;
}