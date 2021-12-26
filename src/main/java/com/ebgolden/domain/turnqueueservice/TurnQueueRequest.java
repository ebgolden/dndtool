package com.ebgolden.domain.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Party;

@Builder
@Value
public class TurnQueueRequest {
    Party party;
}