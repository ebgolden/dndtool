package domain.locationservice;

public interface ChangeVisibilityOfLocationDetails {
    /**
     * Returns a ChangeVisibilityOfLocationDetailsResponse containing a Visibility map.
     * Accepts a Location object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfLocationDetailsRequest.
     * @param changeVisibilityOfUpdatedLocationRequest ChangeVisibilityOfLocationDetailsRequest
     *                                                 containing Location object, Visibility
     *                                                 map, and Player object
     * @return ChangeVisibilityOfLocationDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfLocationDetailsResponse getChangeVisibilityOfLocationDetailsResponse(ChangeVisibilityOfLocationDetailsRequest changeVisibilityOfUpdatedLocationRequest);
}