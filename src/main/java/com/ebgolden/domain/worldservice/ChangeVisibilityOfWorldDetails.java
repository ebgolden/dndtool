package com.ebgolden.domain.worldservice;

public interface ChangeVisibilityOfWorldDetails {
    /**
     * Returns a ChangeVisibilityOfWorldDetailsResponse containing a Visibility map.
     * Accepts a World object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfWorldDetailsRequest.
     * @param changeVisibilityOfGetUpdatedWorldRequest ChangeVisibilityOfWorldDetailsRequest
     *                                                 containing World object, Visibility
     *                                                 map, and Player object
     * @return ChangeVisibilityOfWorldDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfWorldDetailsResponse getChangeVisibilityOfWorldDetailsResponse(ChangeVisibilityOfWorldDetailsRequest changeVisibilityOfGetUpdatedWorldRequest);
}