package services.actionservice;

public interface GetActionFromNonStandardAction {
    /**
     * Returns a ActionFromNonStandardActionResponse containing a Action object.
     * Accepts a NonStandardAction object, a Character object, and a Player object
     * in a ActionFromNonStandardActionRequest.
     * @param actionFromNonStandardActionRequest ActionFromNonStandardActionRequest
     *                                           containing NonStandardAction
     *                                           object, Character object, and
     *                                           Player object
     * @return ActionFromNonStandardActionResponse containing Action object
     */
    ActionFromNonStandardActionResponse getActionFromNonStandardActionResponse(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest);
}