package services.locationdetailservice;

public interface GetLocationDetails {
    /**
     * Returns a LocationDetailsResponse containing a Location object.
     * Accepts a Location object and a Player object in a
     * LocationDetailsRequest.
     * @param locationDetailsRequest LocationDetailsRequest containing
     *                               Location object and Player object
     * @return LocationDetailsResponse containing Location object
     */
    LocationDetailsResponse getLocationDetailsResponse(LocationDetailsRequest locationDetailsRequest);
}