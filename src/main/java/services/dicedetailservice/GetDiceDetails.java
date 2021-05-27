package services.dicedetailservice;

public interface GetDiceDetails {
    /**
     * Returns a DiceDetailsResponse containing an array of Die objects.
     * Accepts an array of Die objects and a Player object in a
     * DiceDetailsRequest.
     * @param diceDetailsRequest DiceDetailsRequest containing array of
     *                           Die Objects and Player object
     * @return DiceDetailsResponse containing array of Die objects
     */
    DiceDetailsResponse getDiceDetailsResponse(DiceDetailsRequest diceDetailsRequest);
}