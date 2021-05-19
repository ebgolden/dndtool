package services.actionservice;

public interface GetActions {
    /**
     * Returns a ActionsResponse containing an array of Action objects.
     * Accepts a Character object in a ActionsRequest.
     * @param actionsRequest ActionsRequest containing Character object
     * @return ActionsResponse containing an array of Action objects
     */
    ActionsResponse getActionsResponse(ActionsRequest actionsRequest);
}