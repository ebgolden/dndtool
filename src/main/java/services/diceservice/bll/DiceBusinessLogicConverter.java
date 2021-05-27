package services.diceservice.bll;

import services.diceservice.UpdatedDiceRequest;
import services.diceservice.UpdatedDiceResponse;
import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogicConverter {
    DiceAndPlayerBo getDiceAndPlayerBoFromUpdatedDiceRequest(UpdatedDiceRequest updatedDiceRequest);

    UpdatedDiceResponse getUpdatedDiceResponseFromDiceBo(DiceBo diceBo);
}