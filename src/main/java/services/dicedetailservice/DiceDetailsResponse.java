package services.dicedetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Die;

@Builder
@Value
public class DiceDetailsResponse {
    Die[] dice;
}