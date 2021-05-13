package services.diceservice;

import com.google.inject.Inject;
import services.diceservice.bll.DiceBusinessLogic;
import services.diceservice.bll.DiceBusinessLogicConverter;
import services.diceservice.bll.bo.DiceRollsBo;

public class AutoRollDiceImpl implements AutoRollDice {
    @Inject
    private DiceBusinessLogicConverter diceBusinessLogicConverter;
    @Inject
    private DiceBusinessLogic diceBusinessLogic;

    public DiceResponse getDiceResponse(AutoRollDiceRequest autoRollDiceRequest) {
        DiceRollsBo diceRollsObject = diceBusinessLogicConverter.getDiceRollsObjectFromAutoRollDiceRequest(autoRollDiceRequest);
        diceRollsObject = diceBusinessLogic.populateDiceRollsObject(diceRollsObject);
        return diceBusinessLogicConverter.getDiceResponseFromDiceRollsObject(diceRollsObject);
    }
}