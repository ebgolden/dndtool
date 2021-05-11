package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.InputDiceRequest;

public interface DiceBusinessLogicConverter {
    DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest);
}