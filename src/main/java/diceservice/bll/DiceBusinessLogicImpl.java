package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.bll.blo.DiceRollsObject;

import java.util.concurrent.ThreadLocalRandom;

public class DiceBusinessLogicImpl implements DiceBusinessLogic {
    public DiceResponse filterDiceResponse(DiceResponse diceResponse) {
        for (int diceRollIndex = 0; diceRollIndex < diceResponse.getDiceRolls().length; ++diceRollIndex)
        {
            if (diceResponse.getDiceRolls()[diceRollIndex] < 0)
                diceResponse.getDiceRolls()[diceRollIndex] *= -1;
            else if (diceResponse.getDiceRolls()[diceRollIndex] == 0)
                diceResponse.getDiceRolls()[diceRollIndex] = 1;
        }
        return diceResponse;
    }

    public DiceRollsObject populateDiceRollsObject(DiceRollsObject diceRollsObject) {
        for (int dieIndex = 0; dieIndex < diceRollsObject.getDiceRolls().length; ++dieIndex) {
            int dieSize = diceRollsObject.getDiceRolls()[dieIndex];
            diceRollsObject.getDiceRolls()[dieIndex] = ThreadLocalRandom.current().nextInt(1, dieSize + 1);
        }
        return diceRollsObject;
    }
}