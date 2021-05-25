package services.characterdetailservice;

public interface UpdateCharacterDetailsVisibility {
    /**
     * Returns a CharacterDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Character object, a Visibility map, and a Player object in a
     * CharacterDetailsVisibilityRequest.
     * @param characterDetailsVisibilityRequest CharacterDetailsVisibilityRequest
     *                                          containing Character object,
     *                                          Visibility map, and Player object
     * @return CharacterDetailsVisibilityResponse containing Visibility map
     */
    CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponse(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest);
}