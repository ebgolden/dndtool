package domain.diceservice.bll;

import domain.diceservice.UpdatedDiceRequest;
import domain.diceservice.UpdatedDiceResponse;
import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogicConverter {
    DiceAndPlayerBo getDiceAndPlayerBoFromUpdatedDiceRequest(UpdatedDiceRequest updatedDiceRequest);

    UpdatedDiceResponse getUpdatedDiceResponseFromDiceBo(DiceBo diceBo);
}