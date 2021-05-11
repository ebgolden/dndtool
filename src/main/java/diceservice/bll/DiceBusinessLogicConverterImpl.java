package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.InputDiceRequest;

public class DiceBusinessLogicConverterImpl implements DiceBusinessLogicConverter {
    public DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest) {
        return DiceResponse
                .builder()
                .diceRolls(inputDiceRequest.getDiceRolls())
                .build();
    }
}