package services.actionservice;

public interface GetActions {
    /**
     * Returns a ActionsResponse containing an array of Action objects.
     * Accepts a CharacterObject object in an ActionsRequest.
     * @param actionsRequest ActionsRequest containing CharacterObject object
     * @return ActionsResponse containing an array of Action objects
     */
    ActionsResponse getActionsResponse(ActionsRequest actionsRequest);
}