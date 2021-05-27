package services.locationservice;

public interface GetUpdatedLocation {
    /**
     * Returns a UpdatedLocationResponse containing a Location object.
     * Accepts a Location object and a Player object in a
     * UpdatedLocationRequest.
     * @param updatedLocationRequest UpdatedLocationRequest containing
     *                               Location object and Player object
     * @return UpdatedLocationResponse containing Location object
     */
    UpdatedLocationResponse getUpdatedLocationResponse(UpdatedLocationRequest updatedLocationRequest);
}