package services.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class TurnQueueBo {
    CharacterObject[] characters;
    int currentTurnIndex;
}