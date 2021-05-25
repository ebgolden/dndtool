package services.locationdetailservice;

public interface UpdateLocationDetailsVisibility {
    /**
     * Returns a LocationDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Location object, a Visibility map, and a Player object in a
     * LocationDetailsVisibilityRequest.
     * @param locationDetailsVisibilityRequest LocationDetailsVisibilityRequest
     *                                         containing Location object,
     *                                         Visibility map, and Player
     *                                         object
     * @return LocationDetailsVisibilityResponse containing Visibility map
     */
    LocationDetailsVisibilityResponse getLocationDetailsVisibilityResponse(LocationDetailsVisibilityRequest locationDetailsVisibilityRequest);
}