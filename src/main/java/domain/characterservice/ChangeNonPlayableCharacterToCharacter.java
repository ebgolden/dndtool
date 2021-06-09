package domain.characterservice;

public interface ChangeNonPlayableCharacterToCharacter {
    /**
     * Returns a ChangeNonPlayableCharacterToCharacterResponse containing a Character object.
     * Accepts a NonPlayableCharacter object and a DungeonMaster object in a
     * ChangeNonPlayableCharacterToCharacterRequest.
     * @param changeNonPlayableCharacterToCharacterRequest ChangeNonPlayableCharacterToCharacterRequest
     *                                                     containing NonPlayableCharacter object and
     *                                                     DungeonMaster object
     * @return ChangeNonPlayableCharacterToCharacterResponse containing Character object
     */
    ChangeNonPlayableCharacterToCharacterResponse getChangeNonPlayableCharacterToCharacterResponse(ChangeNonPlayableCharacterToCharacterRequest changeNonPlayableCharacterToCharacterRequest);
}