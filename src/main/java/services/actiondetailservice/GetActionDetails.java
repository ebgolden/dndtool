package services.actiondetailservice;

public interface GetActionDetails {
    /**
     * Returns a ActionDetailsResponse containing a Action object.
     * Accepts a Action object and a Player object in a
     * ActionDetailsRequest.
     * @param actionDetailsRequest ActionDetailsRequest containing
     *                             Action object and Player object
     * @return ActionDetailsResponse containing Action object
     */
    ActionDetailsResponse getActionDetailsResponse(ActionDetailsRequest actionDetailsRequest);
}