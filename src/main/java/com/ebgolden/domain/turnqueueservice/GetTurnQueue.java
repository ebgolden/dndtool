package com.ebgolden.domain.turnqueueservice;

public interface GetTurnQueue {
    /**
     * Returns a TurnQueueResponse containing an ordered array of Character objects and the current
     * turn index.
     * Accepts a Party object in a TurnQueueRequest.
     * @param turnQueueRequest TurnQueueRequest containing Party object
     * @return TurnQueueResponse containing an ordered array of Character objects and the current
     * turn index
     */
    TurnQueueResponse getTurnQueueResponse(TurnQueueRequest turnQueueRequest);
}