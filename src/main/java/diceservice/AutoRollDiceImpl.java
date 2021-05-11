package diceservice;

import com.google.inject.Inject;
import diceservice.bll.DiceBusinessLogic;
import diceservice.bll.DiceBusinessLogicConverter;
import diceservice.bll.blo.DiceRollsObject;

public class AutoRollDiceImpl implements AutoRollDice {
    @Inject
    private DiceBusinessLogicConverter diceBusinessLogicConverter;
    @Inject
    private DiceBusinessLogic diceBusinessLogic;

    public DiceResponse getDiceResponse(AutoRollDiceRequest autoRollDiceRequest) {
        DiceRollsObject diceRollsObject = diceBusinessLogicConverter.getDiceRollsObjectFromAutoRollDiceRequest(autoRollDiceRequest);
        diceRollsObject = diceBusinessLogic.populateDiceRollsObject(diceRollsObject);
        return diceBusinessLogicConverter.getDiceResponseFromDiceRollsObject(diceRollsObject);
    }
}