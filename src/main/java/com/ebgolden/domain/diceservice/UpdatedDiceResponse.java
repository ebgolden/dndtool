package com.ebgolden.domain.diceservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Die;

@Builder
@Value
public class UpdatedDiceResponse {
    Die[] dice;
}