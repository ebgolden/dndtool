package com.ebgolden.domain.diceservice.bll;

import com.ebgolden.domain.diceservice.UpdatedDiceRequest;
import com.ebgolden.domain.diceservice.UpdatedDiceResponse;
import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogicConverter {
    DiceAndPlayerBo getDiceAndPlayerBoFromUpdatedDiceRequest(UpdatedDiceRequest updatedDiceRequest);

    UpdatedDiceResponse getUpdatedDiceResponseFromDiceBo(DiceBo diceBo);
}