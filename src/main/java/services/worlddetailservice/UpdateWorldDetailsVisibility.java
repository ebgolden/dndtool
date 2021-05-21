package services.worlddetailservice;

public interface UpdateWorldDetailsVisibility {
    /**
     * Returns a WorldDetailsVisibilityResponse containing a visibility map.
     * Accepts a World object, a visibility map, and a Player object in a
     * WorldDetailsVisibilityRequest.
     * @param worldDetailsVisibilityRequest WorldDetailsVisibilityRequest
     *                                      containing World object,
     *                                      visibility map, and Player
     *                                      object
     * @return WorldDetailsVisibilityResponse containing visibility map
     */
    WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest);
}