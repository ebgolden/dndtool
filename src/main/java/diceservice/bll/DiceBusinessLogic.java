package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.bll.blo.DiceRollsObject;

public interface DiceBusinessLogic {
    DiceResponse filterDiceResponse(DiceResponse diceResponse);

    DiceRollsObject populateDiceRollsObject(DiceRollsObject diceRollsObject);
}