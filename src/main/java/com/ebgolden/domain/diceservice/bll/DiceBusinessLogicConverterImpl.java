package com.ebgolden.domain.diceservice.bll;

import com.ebgolden.common.Die;
import com.ebgolden.common.Player;
import com.ebgolden.domain.diceservice.UpdatedDiceResponse;
import com.ebgolden.domain.diceservice.UpdatedDiceRequest;
import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;

public class DiceBusinessLogicConverterImpl implements DiceBusinessLogicConverter {
    public DiceAndPlayerBo getDiceAndPlayerBoFromUpdatedDiceRequest(UpdatedDiceRequest updatedDiceRequest) {
        Die[] dice = updatedDiceRequest.getDice();
        Player player = updatedDiceRequest.getPlayer();
        return DiceAndPlayerBo
                .builder()
                .dice(dice)
                .player(player)
                .build();
    }

    public UpdatedDiceResponse getUpdatedDiceResponseFromDiceBo(DiceBo diceBo) {
        Die[] dice = diceBo.getDice();
        return UpdatedDiceResponse
                .builder()
                .dice(dice)
                .build();
    }
}