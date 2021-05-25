package services.worlddetailservice;

public interface UpdateWorldDetailsVisibility {
    /**
     * Returns a WorldDetailsVisibilityResponse containing a Visibility map.
     * Accepts a World object, a Visibility map, and a Player object in a
     * WorldDetailsVisibilityRequest.
     * @param worldDetailsVisibilityRequest WorldDetailsVisibilityRequest
     *                                      containing World object,
     *                                      Visibility map, and Player
     *                                      object
     * @return WorldDetailsVisibilityResponse containing Visibility map
     */
    WorldDetailsVisibilityResponse getWorldDetailsVisibilityResponse(WorldDetailsVisibilityRequest worldDetailsVisibilityRequest);
}