package services.spelldetailservice;

public interface UpdateSpellDetailsVisibility {
    /**
     * Returns a SpellDetailsVisibilityResponse containing a visibility map.
     * Accepts a Spell object, a visibility map, and a Player object in a
     * SpellDetailsVisibilityRequest.
     * @param spellDetailsVisibilityRequest SpellDetailsVisibilityRequest
     *                                      containing Spell object,
     *                                      visibility map, and Player
     *                                      object
     * @return SpellDetailsVisibilityResponse containing visibility map
     */
    SpellDetailsVisibilityResponse getSpellDetailsVisibilityResponse(SpellDetailsVisibilityRequest spellDetailsVisibilityRequest);
}