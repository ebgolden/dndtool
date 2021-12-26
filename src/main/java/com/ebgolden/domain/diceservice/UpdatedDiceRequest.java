package com.ebgolden.domain.diceservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Die;
import com.ebgolden.common.Player;

@Builder
@Value
public class UpdatedDiceRequest {
    Die[] dice;
    Player player;
}