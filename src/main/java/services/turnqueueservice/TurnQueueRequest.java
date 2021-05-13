package services.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import objects.Party;

@Builder
@Value
public class TurnQueueRequest {
    Party party;
}