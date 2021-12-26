package domain.characterservice;

public interface ChangeVisibilityOfCharacterDetails {
    /**
     * Returns a ChangeVisibilityOfCharacterDetailsResponse containing a Visibility map.
     * Accepts a Character object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfCharacterDetailsRequest.
     * @param changeVisibilityOfUpdatedCharacterRequest ChangeVisibilityOfCharacterDetailsRequest
     *                                                  containing Character object, Visibility
     *                                                  map, and Player object
     * @return ChangeVisibilityOfCharacterDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfCharacterDetailsResponse getChangeVisibilityOfCharacterDetailsResponse(ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest);
}