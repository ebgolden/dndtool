package services.diceservice;

public interface InputDice {
    /**
     * Returns a DiceResponse containing an array of positive, non-zero integer
     * dice rolls.  Accepts an array of dice rolls in an InputDiceRequest.
     * Negative dice rolls are made positive and dice rolls of zero are made one
     * @param inputDiceRequest InputDiceRequest containing integer array of dice rolls
     * @return DiceResponse containing an array of positive, non-zero integer dice rolls
     */
    DiceResponse getDiceResponse(InputDiceRequest inputDiceRequest);
}