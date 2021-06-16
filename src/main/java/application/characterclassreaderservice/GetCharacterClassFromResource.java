package application.characterclassreaderservice;

public interface GetCharacterClassFromResource {
    /**
     * Returns a CharacterClassFromResourceResponse containing a CharacterClass object.
     * Accepts a characterClassName String in a CharacterClassFromResourceRequest.
     * @param characterClassFromResourceRequest CharacterClassFromResourceRequest
     *                                          containing characterClassName String
     * @return CharacterClassFromResourceResponse containing CharacterClass object
     */
    CharacterClassFromResourceResponse getCharacterClassFromResourceResponse(CharacterClassFromResourceRequest characterClassFromResourceRequest);
}