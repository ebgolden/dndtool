package domain.actionservice;

public interface GetActionFromNonStandardAction {
    /**
     * Returns a ActionFromNonStandardActionResponse containing a Action object.
     * Accepts a NonStandardAction object, a Character object, a Player object,
     * and a approvedByDungeonMaster boolean in a
     * ActionFromNonStandardActionRequest.
     * @param actionFromNonStandardActionRequest ActionFromNonStandardActionRequest
     *                                           containing NonStandardAction
     *                                           object, Character object, Player
     *                                           object, and
     *                                           approvedByDungeonMaster boolean
     * @return ActionFromNonStandardActionResponse containing Action object
     */
    ActionFromNonStandardActionResponse getActionFromNonStandardActionResponse(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest);
}