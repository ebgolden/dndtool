package services.actionservice;

public interface TakeAction {
    /**
     * Returns a TakeActionResponse containing a Result object.
     * Accepts a Action object and an array of dice rolls in a
     * TakeActionRequest.
     * @param takeActionRequest TakeActionRequest containing
     *                          Action object and array of
     *                          dice rolls
     * @return TakeActionResponse containing Result object
     */
    TakeActionResponse getTakeActionResponse(TakeActionRequest takeActionRequest);
}