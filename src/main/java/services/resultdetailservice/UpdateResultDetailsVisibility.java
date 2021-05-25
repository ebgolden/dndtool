package services.resultdetailservice;

public interface UpdateResultDetailsVisibility {
    /**
     * Returns a ResultDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Result object, a Visibility map, and a Player object in a
     * ResultDetailsVisibilityRequest.
     * @param resultDetailsVisibilityRequest ResultDetailsVisibilityRequest
     *                                       containing Result object,
     *                                       Visibility map, and Player
     *                                       object
     * @return ResultDetailsVisibilityResponse containing Visibility map
     */
    ResultDetailsVisibilityResponse getResultDetailsVisibilityResponse(ResultDetailsVisibilityRequest resultDetailsVisibilityRequest);
}