package services.diceservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Die;

@Builder
@Value
public class UpdatedDiceResponse {
    Die[] dice;
}