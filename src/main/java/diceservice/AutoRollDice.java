package diceservice;

public interface AutoRollDice {
    /**
     * Returns a DiceResponse containing an array of positive, non-zero integer
     * dice rolls.  Accepts an array of encoded strings.
     * @param autoRollDiceRequest AutoRollDiceRequest containing string array
     *                            encoded as nds, where n is the number of
     *                            dice with s sides, and d is the delimiter
     * @return DiceResponse containing an array of positive, non-zero integer dice rolls
     */
    DiceResponse getDiceResponse(AutoRollDiceRequest autoRollDiceRequest);
}