package domain.diceservice;

import lombok.Builder;
import lombok.Value;
import common.Die;

@Builder
@Value
public class UpdatedDiceResponse {
    Die[] dice;
}