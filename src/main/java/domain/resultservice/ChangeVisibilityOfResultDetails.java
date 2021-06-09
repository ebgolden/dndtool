package domain.resultservice;

public interface ChangeVisibilityOfResultDetails {
    /**
     * Returns a ChangeVisibilityOfResultDetailsResponse containing a Visibility map.
     * Accepts a Result object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfResultDetailsRequest.
     * @param changeVisibilityOfUpdatedResultRequest ChangeVisibilityOfResultDetailsRequest
     *                                               containing Result object, Visibility
     *                                               map, and Player object
     * @return ChangeVisibilityOfResultDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfResultDetailsResponse getChangeVisibilityOfResultDetailsResponse(ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest);
}