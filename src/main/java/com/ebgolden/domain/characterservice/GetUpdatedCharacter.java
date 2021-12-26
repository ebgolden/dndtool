package com.ebgolden.domain.characterservice;

public interface GetUpdatedCharacter {
    /**
     * Returns a UpdatedCharacterResponse containing a Character object.
     * Accepts a Character object and a Player object in a
     * UpdatedCharacterRequest.
     * @param updatedCharacterRequest UpdatedCharacterRequest containing
     *                                Character object and Player object
     * @return UpdatedCharacterResponse containing Character object
     */
    UpdatedCharacterResponse getUpdatedCharacterResponse(UpdatedCharacterRequest updatedCharacterRequest);
}