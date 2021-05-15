package services.characterdetailservice;

public interface GetCharacterDetails {
    /**
     * Returns a CharacterDetailsResponse containing a CharacterObject object.
     * Accepts a CharacterObject object in an CharacterDetailsRequest.
     * @param characterDetailsRequest CharacterDetailsRequest containing CharacterObject object
     * @return CharacterDetailsResponse containing a CharacterObject object
     */
    CharacterDetailsResponse getCharacterDetailsResponse(CharacterDetailsRequest characterDetailsRequest);
}