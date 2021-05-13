package services.diceservice;

import com.google.inject.Inject;
import services.diceservice.bll.DiceBusinessLogic;
import services.diceservice.bll.DiceBusinessLogicConverter;

public class InputDiceImpl implements InputDice {
    @Inject
    private DiceBusinessLogicConverter diceBusinessLogicConverter;
    @Inject
    private DiceBusinessLogic diceBusinessLogic;

    public DiceResponse getDiceResponse(InputDiceRequest inputDiceRequest) {
        DiceResponse diceResponse = diceBusinessLogicConverter.getDiceResponseFromInputDiceRequest(inputDiceRequest);
        return diceBusinessLogic.filterDiceResponse(diceResponse);
    }
}