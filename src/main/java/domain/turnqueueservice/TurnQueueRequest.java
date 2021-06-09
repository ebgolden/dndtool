package domain.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import common.Party;

@Builder
@Value
public class TurnQueueRequest {
    Party party;
}