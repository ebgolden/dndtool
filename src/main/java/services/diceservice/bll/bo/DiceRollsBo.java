package services.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DiceRollsBo {
    int[] diceRolls;
}