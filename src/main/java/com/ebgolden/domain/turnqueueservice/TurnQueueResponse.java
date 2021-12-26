package domain.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import common.Character;

@Builder
@Value
public class TurnQueueResponse {
    Character[] characters;
    int currentTurnIndex;
}