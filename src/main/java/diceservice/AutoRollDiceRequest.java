package diceservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AutoRollDiceRequest {
    String[] encodedDice;
}