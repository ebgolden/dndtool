package services.characterdetailservice;

public interface UpdateCharacterDetailsVisibility {
    /**
     * Returns a CharacterDetailsVisibilityResponse containing a visibility json string.
     * Accepts a Character object, a visibility json string, and a Player object in a
     * CharacterDetailsVisibilityRequest.
     * @param characterDetailsVisibilityRequest CharacterDetailsVisibilityRequest
     *                                          containing Character object, visibility
     *                                          json string, and Player object
     * @return CharacterDetailsVisibilityResponse containing visibility json string
     */
    CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponse(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest);
}