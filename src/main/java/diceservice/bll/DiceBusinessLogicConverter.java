package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.InputDiceRequest;

public class DiceBusinessLogicConverter {
    public DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest) {
        DiceResponse diceResponse = new DiceResponse();
        diceResponse.diceRolls = inputDiceRequest.diceRolls;
        return diceResponse;
    }
}