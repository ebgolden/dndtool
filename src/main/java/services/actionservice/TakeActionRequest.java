package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Action;

@Builder
@Value
public class TakeActionRequest {
    Action action;
    int[] diceRolls;
}