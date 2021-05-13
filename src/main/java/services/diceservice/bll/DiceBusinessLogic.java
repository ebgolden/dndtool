package services.diceservice.bll;

import services.diceservice.DiceResponse;
import services.diceservice.bll.bo.DiceRollsBo;

public interface DiceBusinessLogic {
    DiceResponse filterDiceResponse(DiceResponse diceResponse);

    DiceRollsBo populateDiceRollsObject(DiceRollsBo diceRollsObject);
}