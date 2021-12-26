package com.ebgolden.domain.characterclassservice;

public interface GetUpdatedCharacterClass {
    /**
     * Returns a UpdatedCharacterClassResponse containing a CharacterClass object.
     * Accepts a CharacterClass object in a UpdatedCharacterClassRequest.
     * @param updatedCharacterClassRequest UpdatedCharacterClassRequest containing
     *                            CharacterClass object
     * @return UpdatedCharacterClassResponse containing CharacterClass object
     */
    UpdatedCharacterClassResponse getUpdatedCharacterClassResponse(UpdatedCharacterClassRequest updatedCharacterClassRequest);
}