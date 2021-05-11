package diceservice;

import diceservice.bll.DiceBusinessLogic;
import diceservice.bll.DiceBusinessLogicConverter;

public class InputDice {
    private DiceBusinessLogicConverter diceBusinessLogicConverter;
    private DiceBusinessLogic diceBusinessLogic;

    public InputDice() {
        diceBusinessLogicConverter = new DiceBusinessLogicConverter();
        diceBusinessLogic = new DiceBusinessLogic();
    }

    /**
     * Returns a DiceResponse containing an array of positive, non-zero integer
     * dice rolls.  Accepts an array of dice rolls in an InputDiceRequest.
     * Negative dice rolls are made positive and dice rolls of zero are made one
     * @param inputDiceRequest InputDiceRequest containing integer array of dice rolls
     * @return DiceResponse containing an array of positive, non-zero integer dice rolls
     */
    public DiceResponse getDiceResponse(InputDiceRequest inputDiceRequest) {
        DiceResponse diceResponse = diceBusinessLogicConverter.getDiceResponseFromInputDiceRequest(inputDiceRequest);
        return diceBusinessLogic.filterDiceResponse(diceResponse);
    }
}