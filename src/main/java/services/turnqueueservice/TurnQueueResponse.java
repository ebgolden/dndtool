package services.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class TurnQueueResponse {
    CharacterObject[] characters;
    int currentTurnIndex;
}