package com.ebgolden.domain.diceservice;

import com.google.inject.Inject;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogic;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogicConverter;
import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;

public class GetUpdatedDiceImpl implements GetUpdatedDice {
    @Inject
    private DiceBusinessLogicConverter diceBusinessLogicConverter;
    @Inject
    private DiceBusinessLogic diceBusinessLogic;

    public UpdatedDiceResponse getUpdatedDiceResponse(UpdatedDiceRequest updatedDiceRequest) {
        DiceAndPlayerBo diceAndPlayerBo = diceBusinessLogicConverter.getDiceAndPlayerBoFromUpdatedDiceRequest(updatedDiceRequest);
        DiceBo diceBo = diceBusinessLogic.getDiceBo(diceAndPlayerBo);
        return diceBusinessLogicConverter.getUpdatedDiceResponseFromDiceBo(diceBo);
    }
}