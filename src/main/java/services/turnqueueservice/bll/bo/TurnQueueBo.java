package services.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;

@Builder
@Value
public class TurnQueueBo {
    Character[] characters;
    int currentTurnIndex;
}