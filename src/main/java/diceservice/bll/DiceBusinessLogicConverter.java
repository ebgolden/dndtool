package diceservice.bll;

import diceservice.AutoRollDiceRequest;
import diceservice.DiceResponse;
import diceservice.InputDiceRequest;
import diceservice.bll.blo.DiceRollsObject;

public interface DiceBusinessLogicConverter {
    DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest);

    DiceRollsObject getDiceRollsObjectFromAutoRollDiceRequest(AutoRollDiceRequest autoRollDiceRequest);

    DiceResponse getDiceResponseFromDiceRollsObject(DiceRollsObject diceRollsObject);
}