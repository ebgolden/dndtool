package services.worlddetailservice;

public interface UpdateWorldDetailsVisibility {
    /**
     * Returns a WorldDetailsVisibilityResponse containing a visibility json string.
     * Accepts a World object, a visibility json string, and a Player object in a
     * WorldDetailsVisibilityRequest.
     * @param worldDetailsVisibilityRequest WorldDetailsVisibilityRequest containing
     *                                      World object, visibility json string,
     *                                      and Player object
     * @return WorldDetailsVisibilityResponse containing visibility json string
     */
    WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest);
}