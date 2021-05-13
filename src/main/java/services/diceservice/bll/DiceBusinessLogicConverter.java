package services.diceservice.bll;

import services.diceservice.AutoRollDiceRequest;
import services.diceservice.DiceResponse;
import services.diceservice.InputDiceRequest;
import services.diceservice.bll.bo.DiceRollsBo;

public interface DiceBusinessLogicConverter {
    DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest);

    DiceRollsBo getDiceRollsObjectFromAutoRollDiceRequest(AutoRollDiceRequest autoRollDiceRequest);

    DiceResponse getDiceResponseFromDiceRollsObject(DiceRollsBo diceRollsObject);
}