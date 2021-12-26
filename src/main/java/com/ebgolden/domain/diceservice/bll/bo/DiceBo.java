package com.ebgolden.domain.diceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Die;

@Builder
@Value
public class DiceBo {
    Die[] dice;
}