package services.spelldetailservice;

public interface UpdateSpellDetailsVisibility {
    /**
     * Returns a SpellDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Spell object, a Visibility map, and a Player object in a
     * SpellDetailsVisibilityRequest.
     * @param spellDetailsVisibilityRequest SpellDetailsVisibilityRequest
     *                                      containing Spell object,
     *                                      Visibility map, and Player
     *                                      object
     * @return SpellDetailsVisibilityResponse containing Visibility map
     */
    SpellDetailsVisibilityResponse getSpellDetailsVisibilityResponse(SpellDetailsVisibilityRequest spellDetailsVisibilityRequest);
}