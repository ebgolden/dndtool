package services.itemservice;

public interface ChangeVisibilityOfItemDetails {
    /**
     * Returns a ChangeVisibilityOfItemDetailsResponse containing a Visibility map.
     * Accepts a Item object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfItemDetailsRequest.
     * @param changeVisibilityOfUpdatedItemRequest ChangeVisibilityOfItemDetailsRequest
     *                                             containing Item object, Visibility
     *                                             map, and Player object
     * @return ChangeVisibilityOfItemDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfItemDetailsResponse getChangeVisibilityOfItemDetailsResponse(ChangeVisibilityOfItemDetailsRequest changeVisibilityOfUpdatedItemRequest);
}