package services.diceservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DiceResponse {
    int[] diceRolls;
}