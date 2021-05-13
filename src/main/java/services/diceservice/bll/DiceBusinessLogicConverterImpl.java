package services.diceservice.bll;

import services.diceservice.AutoRollDiceRequest;
import services.diceservice.DiceResponse;
import services.diceservice.InputDiceRequest;
import services.diceservice.bll.bo.DiceRollsBo;

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

    public DiceRollsBo getDiceRollsObjectFromAutoRollDiceRequest(AutoRollDiceRequest autoRollDiceRequest) {
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
        return DiceRollsBo
                .builder()
                .diceRolls(diceSizesArray)
                .build();
    }

    public DiceResponse getDiceResponseFromDiceRollsObject(DiceRollsBo diceRollsObject) {
        return DiceResponse
                .builder()
                .diceRolls(diceRollsObject.getDiceRolls())
                .build();
    }
}