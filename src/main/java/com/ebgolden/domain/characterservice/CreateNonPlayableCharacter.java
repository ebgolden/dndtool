package domain.characterservice;

public interface CreateNonPlayableCharacter {
    /**
     * Returns a CreateNonPlayableCharacterResponse containing a NonPlayableCharacter object.
     * Accepts a NonPlayableCharacter object, a Visibility map, and a DungeonMaster object in
     * a CreateCharacterRequest.
     * @param createNonPlayableCharacterRequest CreateNonPlayableCharacterRequest containing
     *                                          NonPlayableCharacter object, Visibility map,
     *                                          and DungeonMaster object
     * @return CreateNonPlayableCharacterResponse containing NonPlayableCharacter object
     */
    CreateNonPlayableCharacterResponse getCreateNonPlayableCharacterResponse(CreateNonPlayableCharacterRequest createNonPlayableCharacterRequest);
}