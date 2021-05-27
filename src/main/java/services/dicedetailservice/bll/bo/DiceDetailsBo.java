package services.dicedetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Die;

@Builder
@Value
public class DiceDetailsBo {
    Die[] dice;
}