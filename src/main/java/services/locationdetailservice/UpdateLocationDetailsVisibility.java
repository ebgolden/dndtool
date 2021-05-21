package services.locationdetailservice;

public interface UpdateLocationDetailsVisibility {
    /**
     * Returns a LocationDetailsVisibilityResponse containing a visibility json string.
     * Accepts a Location object, a visibility json string, and a Player object in a
     * LocationDetailsVisibilityRequest.
     * @param locationDetailsVisibilityRequest LocationDetailsVisibilityRequest
     *                                         containing Location object, visibility
     *                                         json string, and Player object
     * @return LocationDetailsVisibilityResponse containing visibility json string
     */
    LocationDetailsVisibilityResponse getLocationDetailsVisibilityResponse(LocationDetailsVisibilityRequest locationDetailsVisibilityRequest);
}