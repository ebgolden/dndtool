package com.ebgolden.domain.spellservice;

public interface GetUpdatedSpell {
    /**
     * Returns a UpdatedSpellResponse containing a Spell object.
     * Accepts a Spell object and a Player object in a
     * UpdatedSpellRequest.
     * @param updatedSpellRequest UpdatedSpellRequest containing
     *                            Spell object and Player object
     * @return UpdatedSpellResponse containing Spell object
     */
    UpdatedSpellResponse getUpdatedSpellResponse(UpdatedSpellRequest updatedSpellRequest);
}