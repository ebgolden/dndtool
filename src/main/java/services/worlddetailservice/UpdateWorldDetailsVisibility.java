package services.worlddetailservice;

public interface UpdateWorldDetailsVisibility {
    /**
     * Returns a WorldDetailsVisibilityResponse containing a visibility json string.
     * Accepts a World object, a visibility json string, and a Player object
     * in an WorldDetailsVisibilityRequest.
     * @param worldDetailsVisibilityRequest WorldDetailsVisibilityRequest
     *                                          containing a World object, a
     *                                          visibility json string, and a
     *                                          Player object
     * @return WorldDetailsVisibilityResponse containing a visibility json string
     */
    WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest);
}