package services.characterservice;

public interface ChangeCharacterToNonPlayableCharacter {
    /**
     * Returns a ChangeCharacterToNonPlayableCharacterResponse containing a NonPlayableCharacter object.
     * Accepts a Character object and a DungeonMaster object in a
     * ChangeCharacterToNonPlayableCharacterRequest.
     * @param changeCharacterToNonPlayableCharacterRequest ChangeCharacterToNonPlayableCharacterRequest
     *                                                     containing Character object and DungeonMaster
     *                                                     object
     * @return ChangeCharacterToNonPlayableCharacterResponse containing NonPlayableCharacter object
     */
    ChangeCharacterToNonPlayableCharacterResponse getChangeCharacterToNonPlayableCharacterResponse(ChangeCharacterToNonPlayableCharacterRequest changeCharacterToNonPlayableCharacterRequest);
}