package domain.diceservice;

import com.google.inject.Inject;
import domain.diceservice.bll.DiceBusinessLogic;
import domain.diceservice.bll.DiceBusinessLogicConverter;
import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;

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