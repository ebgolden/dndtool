package services.resultdetailservice;

public interface UpdateResultDetailsVisibility {
    /**
     * Returns a ResultDetailsVisibilityResponse containing a visibility map.
     * Accepts a Result object, a visibility map, and a Player object in a
     * ResultDetailsVisibilityRequest.
     * @param resultDetailsVisibilityRequest ResultDetailsVisibilityRequest
     *                                       containing Result object,
     *                                       visibility map, and Player
     *                                       object
     * @return ResultDetailsVisibilityResponse containing visibility map
     */
    ResultDetailsVisibilityResponse getResultDetailsVisibilityResponse(ResultDetailsVisibilityRequest resultDetailsVisibilityRequest);
}