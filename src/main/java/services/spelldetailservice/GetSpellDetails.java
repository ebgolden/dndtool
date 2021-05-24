package services.spelldetailservice;

public interface GetSpellDetails {
    /**
     * Returns a SpellDetailsResponse containing a Spell object.
     * Accepts a Spell object and a Player object in a
     * SpellDetailsRequest.
     * @param spellDetailsRequest SpellDetailsRequest containing
     *                            Spell object and Player object
     * @return SpellDetailsResponse containing Spell object
     */
    SpellDetailsResponse getSpellDetailsResponse(SpellDetailsRequest spellDetailsRequest);
}