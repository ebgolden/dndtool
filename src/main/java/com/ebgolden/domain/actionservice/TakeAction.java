package com.ebgolden.domain.actionservice;

public interface TakeAction {
    /**
     * Returns a TakeActionResponse containing a Result object.
     * Accepts a Action object and an array of Die objects in a
     * TakeActionRequest.
     * @param takeActionRequest TakeActionRequest containing
     *                          Action object and array of
     *                          Die objects
     * @return TakeActionResponse containing Result object
     */
    TakeActionResponse getTakeActionResponse(TakeActionRequest takeActionRequest);
}