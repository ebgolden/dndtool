package diceservice.bll.blo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DiceRollsObject {
    int[] diceRolls;
}