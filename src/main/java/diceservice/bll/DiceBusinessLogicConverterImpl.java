package diceservice.bll;

import diceservice.AutoRollDiceRequest;
import diceservice.DiceResponse;
import diceservice.InputDiceRequest;
import diceservice.bll.blo.DiceRollsObject;

import java.util.ArrayList;
import java.util.List;

public class DiceBusinessLogicConverterImpl implements DiceBusinessLogicConverter {
    public DiceResponse getDiceResponseFromInputDiceRequest(InputDiceRequest inputDiceRequest) {
        int[] diceRolls = new int[] {};
        if (inputDiceRequest != null && inputDiceRequest.getDiceRolls() != null)
            diceRolls = inputDiceRequest.getDiceRolls();
        return DiceResponse
                .builder()
                .diceRolls(diceRolls)
                .build();
    }

    public DiceRollsObject getDiceRollsObjectFromAutoRollDiceRequest(AutoRollDiceRequest autoRollDiceRequest) {
        List<Integer> diceSizes = new ArrayList<>();
        if (autoRollDiceRequest != null && autoRollDiceRequest.getEncodedDice() != null) {
            for (String encodedDice : autoRollDiceRequest.getEncodedDice()) {
                String[] dieAmountAndSize = encodedDice.split("d");
                int amountOfDice = Integer.parseInt(dieAmountAndSize[0]);
                int dieSize = Integer.parseInt(dieAmountAndSize[1]);
                for (int dieIndex = 0; dieIndex < amountOfDice; ++dieIndex)
                    diceSizes.add(dieSize);
            }
        }
        int[] diceSizesArray = diceSizes.stream().mapToInt(i->i).toArray();
        return DiceRollsObject
                .builder()
                .diceRolls(diceSizesArray)
                .build();
    }

    public DiceResponse getDiceResponseFromDiceRollsObject(DiceRollsObject diceRollsObject) {
        return DiceResponse
                .builder()
                .diceRolls(diceRollsObject.getDiceRolls())
                .build();
    }
}