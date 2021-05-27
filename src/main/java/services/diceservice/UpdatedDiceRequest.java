package services.diceservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Die;
import commonobjects.Player;

@Builder
@Value
public class UpdatedDiceRequest {
    Die[] dice;
    Player player;
}