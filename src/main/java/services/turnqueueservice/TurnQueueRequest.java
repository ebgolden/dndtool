package services.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Party;

@Builder
@Value
public class TurnQueueRequest {
    Party party;
}