package com.ebgolden.domain.partyservice;

public interface MergeParties {
    /**
     * Returns a MergePartiesResponse containing a Party object.
     * Accepts an array of Party objects and a DungeonMaster
     * object in a MergePartiesRequest.
     * @param mergePartiesRequest MergePartiesRequest containing
     *                            array of Party objects and
     *                            DungeonMaster object
     * @return MergePartiesResponse containing Party object
     */
    MergePartiesResponse getMergePartiesResponse(MergePartiesRequest mergePartiesRequest);
}