package com.ebgolden.domain.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Die;
import com.ebgolden.common.Player;

@Builder
@Value
public class DiceAndPlayerBo {
    Die[] dice;
    Player player;
}