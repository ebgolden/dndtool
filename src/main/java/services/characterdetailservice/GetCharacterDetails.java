package services.characterdetailservice;

public interface GetCharacterDetails {
    /**
     * Returns a CharacterDetailsResponse containing a Character object.
     * Accepts a Character object and a Player object in an CharacterDetailsRequest.
     * @param characterDetailsRequest CharacterDetailsRequest containing Character
     *                                object and Player object
     * @return CharacterDetailsResponse containing a Character object
     */
    CharacterDetailsResponse getCharacterDetailsResponse(CharacterDetailsRequest characterDetailsRequest);
}