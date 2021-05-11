package diceservice.bll;

import diceservice.DiceResponse;
import diceservice.InputDiceRequest;

public class DiceBusinessLogic {
    public DiceResponse filterDiceResponse(DiceResponse diceResponse) {
        if (diceResponse.diceRolls == null)
            diceResponse.diceRolls = new int[] {};
        for (int diceRollIndex = 0; diceRollIndex < diceResponse.diceRolls.length; ++diceRollIndex)
        {
            if (diceResponse.diceRolls[diceRollIndex] < 0)
                diceResponse.diceRolls[diceRollIndex] *= -1;
            else if (diceResponse.diceRolls[diceRollIndex] == 0)
                diceResponse.diceRolls[diceRollIndex] = 1;
        }
        return diceResponse;
    }
}
