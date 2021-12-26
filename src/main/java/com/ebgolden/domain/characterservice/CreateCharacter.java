package domain.characterservice;

public interface CreateCharacter {
    /**
     * Returns a CreateCharacterResponse containing a Character object.
     * Accepts a Character object, a Visibility map, and a Player
     * object in a CreateCharacterRequest.
     * @param createCharacterRequest CreateCharacterRequest containing
     *                               Character object, Visibility map,
     *                               and Player object
     * @return CreateCharacterResponse containing Character object
     */
    CreateCharacterResponse getCreateCharacterResponse(CreateCharacterRequest createCharacterRequest);
}