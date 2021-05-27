package services.campaignservice;

public interface GetUpdatedCampaign {
    /**
     * Returns a UpdatedCampaignResponse containing a Campaign object.
     * Accepts a Campaign object and a Player object in a
     * UpdatedCampaignRequest.
     * @param updatedCampaignRequest UpdatedCampaignRequest containing
     *                               Campaign object and Player object
     * @return UpdatedCampaignResponse containing Campaign object
     */
    UpdatedCampaignResponse getUpdatedCampaignResponse(UpdatedCampaignRequest updatedCampaignRequest);
}