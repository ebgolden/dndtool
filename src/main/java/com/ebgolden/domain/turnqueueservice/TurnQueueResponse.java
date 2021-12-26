package com.ebgolden.domain.turnqueueservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;

@Builder
@Value
public class TurnQueueResponse {
    Character[] characters;
    int currentTurnIndex;
}