package services.characterdetailservice;

public interface UpdateCharacterDetailsVisibility {
    /**
     * Returns a CharacterDetailsVisibilityResponse containing a visibility map.
     * Accepts a Character object, a visibility map, and a Player object in a
     * CharacterDetailsVisibilityRequest.
     * @param characterDetailsVisibilityRequest CharacterDetailsVisibilityRequest
     *                                          containing Character object,
     *                                          visibility map, and Player object
     * @return CharacterDetailsVisibilityResponse containing visibility map
     */
    CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponse(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest);
}