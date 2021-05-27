package services.diceservice;

import com.google.inject.Inject;
import services.diceservice.bll.DiceBusinessLogic;
import services.diceservice.bll.DiceBusinessLogicConverter;
import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;

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