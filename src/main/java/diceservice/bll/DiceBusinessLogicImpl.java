package diceservice.bll;

import diceservice.DiceResponse;

public class DiceBusinessLogicImpl implements DiceBusinessLogic {
    public DiceResponse filterDiceResponse(DiceResponse diceResponse) {
        DiceResponse temporaryDiceResponse = diceResponse;
        if (temporaryDiceResponse.getDiceRolls() == null)
            temporaryDiceResponse = DiceResponse
                    .builder()
                    .diceRolls(new int[] {})
                    .build();
        for (int diceRollIndex = 0; diceRollIndex < temporaryDiceResponse.getDiceRolls().length; ++diceRollIndex)
        {
            if (temporaryDiceResponse.getDiceRolls()[diceRollIndex] < 0)
                temporaryDiceResponse.getDiceRolls()[diceRollIndex] *= -1;
            else if (temporaryDiceResponse.getDiceRolls()[diceRollIndex] == 0)
                temporaryDiceResponse.getDiceRolls()[diceRollIndex] = 1;
        }
        return temporaryDiceResponse;
    }
}