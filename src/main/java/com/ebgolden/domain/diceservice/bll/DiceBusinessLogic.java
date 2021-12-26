package com.ebgolden.domain.diceservice.bll;

import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogic {
    DiceBo getDiceBo(DiceAndPlayerBo diceAndPlayerBo);
}