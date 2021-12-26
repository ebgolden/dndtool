package com.ebgolden.domain.resultservice;

public interface GetUpdatedResult {
    /**
     * Returns a UpdatedResultResponse containing a Result object.
     * Accepts a Result object and a Player object in a
     * UpdatedResultRequest.
     * @param updatedResultRequest UpdatedResultRequest containing
     *                             Result object and Player object
     * @return UpdatedResultResponse containing Result object
     */
    UpdatedResultResponse getUpdatedResultResponse(UpdatedResultRequest updatedResultRequest);
}