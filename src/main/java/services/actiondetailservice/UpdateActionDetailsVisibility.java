package services.actiondetailservice;

public interface UpdateActionDetailsVisibility {
    /**
     * Returns a ActionDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Action object, a Visibility map, and a Player object in a
     * ActionDetailsVisibilityRequest.
     * @param actionDetailsVisibilityRequest ActionDetailsVisibilityRequest
     *                                       containing Action object,
     *                                       Visibility map, and Player
     *                                       object
     * @return ActionDetailsVisibilityResponse containing Visibility map
     */
    ActionDetailsVisibilityResponse getActionDetailsVisibilityResponse(ActionDetailsVisibilityRequest actionDetailsVisibilityRequest);
}