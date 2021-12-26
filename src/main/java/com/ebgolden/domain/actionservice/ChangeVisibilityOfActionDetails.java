package domain.actionservice;

public interface ChangeVisibilityOfActionDetails {
    /**
     * Returns a ChangeVisibilityOfActionDetailsResponse containing a Visibility map.
     * Accepts a Action object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfActionDetailsRequest.
     * @param changeVisibilityOfActionDetailsRequest ChangeVisibilityOfActionDetailsRequest
     *                                               containing Action object, Visibility
     *                                               map, and Player object
     * @return ChangeVisibilityOfActionDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfActionDetailsResponse getChangeVisibilityOfActionDetailsResponse(ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest);
}