package com.ebgolden.domain.campaignservice;

public interface ChangeVisibilityOfCampaignDetails {
    /**
     * Returns a ChangeVisibilityOfCampaignDetailsResponse containing a Visibility map.
     * Accepts a Campaign object, a Visibility map, and a Player object in a
     * ChangeVisibilityOfCampaignDetailsRequest.
     * @param changeVisibilityOfUpdatedCampaignRequest ChangeVisibilityOfCampaignDetailsRequest
     *                                                 containing Campaign object, Visibility
     *                                                 map, and Player object
     * @return ChangeVisibilityOfCampaignDetailsResponse containing Visibility map
     */
    ChangeVisibilityOfCampaignDetailsResponse getChangeVisibilityOfCampaignDetailsResponse(ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest);
}