package services.characterdetailservice;

public interface GetCharacterDetails {
    /**
     * Returns a CharacterDetailsResponse containing a Character object.
     * Accepts a Character object and a Player object in a
     * CharacterDetailsRequest.
     * @param characterDetailsRequest CharacterDetailsRequest containing
     *                                Character object and Player object
     * @return CharacterDetailsResponse containing Character object
     */
    CharacterDetailsResponse getCharacterDetailsResponse(CharacterDetailsRequest characterDetailsRequest);
}