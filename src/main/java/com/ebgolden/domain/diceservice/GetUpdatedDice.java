package domain.diceservice;

public interface GetUpdatedDice {
    /**
     * Returns a UpdatedDiceResponse containing an array of Die objects.
     * Accepts an array of Die objects and a Player object in a
     * UpdatedDiceRequest.
     * @param updatedDiceRequest UpdatedDiceRequest containing array of
     *                           Die Objects and Player object
     * @return UpdatedDiceResponse containing array of Die objects
     */
    UpdatedDiceResponse getUpdatedDiceResponse(UpdatedDiceRequest updatedDiceRequest);
}