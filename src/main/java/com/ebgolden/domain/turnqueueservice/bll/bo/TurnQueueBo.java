package domain.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;

@Builder
@Value
public class TurnQueueBo {
    Character[] characters;
    int currentTurnIndex;
}