package services.spellservice;

public interface ChangeVisibilityOfSpellDetails {
    /**
     * Returns a ChangeVisibilityOfSpellDetailsResponse containing a Visibility map.
     * Accepts a Spell object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfSpellDetailsRequest.
     * @param changeVisibilityOfUpdatedSpellRequest ChangeVisibilityOfSpellDetailsRequest
     *                                              containing Spell object, Visibility
     *                                              map, and Player object
     * @return ChangeVisibilityOfSpellDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfSpellDetailsResponse getChangeVisibilityOfSpellDetailsResponse(ChangeVisibilityOfSpellDetailsRequest changeVisibilityOfUpdatedSpellRequest);
}