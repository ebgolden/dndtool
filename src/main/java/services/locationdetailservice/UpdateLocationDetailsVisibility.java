package services.locationdetailservice;

public interface UpdateLocationDetailsVisibility {
    /**
     * Returns a LocationDetailsVisibilityResponse containing a visibility map.
     * Accepts a Location object, a visibility map, and a Player object in a
     * LocationDetailsVisibilityRequest.
     * @param locationDetailsVisibilityRequest LocationDetailsVisibilityRequest
     *                                         containing Location object,
     *                                         visibility map, and Player
     *                                         object
     * @return LocationDetailsVisibilityResponse containing visibility map
     */
    LocationDetailsVisibilityResponse getLocationDetailsVisibilityResponse(LocationDetailsVisibilityRequest locationDetailsVisibilityRequest);
}