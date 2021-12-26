package com.ebgolden.domain.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;

@Builder
@Value
public class TurnQueueBo {
    Character[] characters;
    int currentTurnIndex;
}