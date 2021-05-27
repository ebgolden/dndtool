package services.actionservice;

public interface GetUpdatedAction {
    /**
     * Returns a UpdatedActionResponse containing a Action object.
     * Accepts a Action object and a Player object in a
     * UpdatedActionRequest.
     * @param updatedActionRequest UpdatedActionRequest containing
     *                             Action object and Player object
     * @return UpdatedActionResponse containing Action object
     */
    UpdatedActionResponse getUpdatedActionResponse(UpdatedActionRequest updatedActionRequest);
}