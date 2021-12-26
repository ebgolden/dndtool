package com.ebgolden.domain.campaignservice;

public interface CreateCampaign {
    /**
     * Returns a CreateCampaignResponse containing a Campaign object.
     * Accepts a Campaign object and a DungeonMaster object in a
     * CreateCampaignRequest.
     * @param createCampaignRequest CreateCampaignRequest containing
     *                              Campaign object and Player object
     * @return CreateCampaignResponse containing Campaign object
     */
    CreateCampaignResponse getCreateCampaignResponse(CreateCampaignRequest createCampaignRequest);
}