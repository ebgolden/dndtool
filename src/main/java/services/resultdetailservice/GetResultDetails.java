package services.resultdetailservice;

public interface GetResultDetails {
    /**
     * Returns a ResultDetailsResponse containing a Result object.
     * Accepts a Result object and a Player object in a
     * ResultDetailsRequest.
     * @param resultDetailsRequest ResultDetailsRequest containing
     *                             Result object and Player object
     * @return ResultDetailsResponse containing Result object
     */
    ResultDetailsResponse getResultDetailsResponse(ResultDetailsRequest resultDetailsRequest);
}