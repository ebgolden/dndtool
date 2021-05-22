package services.actiondetailservice;

public interface UpdateActionDetailsVisibility {
    /**
     * Returns a ActionDetailsVisibilityResponse containing a visibility map.
     * Accepts a Action object, a visibility map, and a Player object in a
     * ActionDetailsVisibilityRequest.
     * @param actionDetailsVisibilityRequest ActionDetailsVisibilityRequest
     *                                       containing Action object,
     *                                       visibility map, and Player
     *                                       object
     * @return ActionDetailsVisibilityResponse containing visibility map
     */
    ActionDetailsVisibilityResponse getActionDetailsVisibilityResponse(ActionDetailsVisibilityRequest actionDetailsVisibilityRequest);
}