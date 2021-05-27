package services.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;

@Builder
@Value
public class TurnQueueResponse {
    Character[] characters;
    int currentTurnIndex;
}