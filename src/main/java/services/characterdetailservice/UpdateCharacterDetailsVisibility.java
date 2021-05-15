package services.characterdetailservice;

public interface UpdateCharacterDetailsVisibility {
    /**
     * Returns a CharacterDetailsVisibilityResponse containing a visibility json string.
     * Accepts a CharacterObject object, a visibility json string, and a Player object
     * in an CharacterDetailsVisibilityRequest.
     * @param characterDetailsVisibilityRequest CharacterDetailsVisibilityRequest
     *                                          containing a CharacterObject object,
     *                                          a visibility json string, and a
     *                                          Player object
     * @return CharacterDetailsVisibilityResponse containing a visibility json string
     */
    CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponse(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest);
}