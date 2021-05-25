package services.campaigndetailservice;

public interface UpdateCampaignDetailsVisibility {
    /**
     * Returns a CampaignDetailsVisibilityResponse containing a Visibility map.
     * Accepts a Campaign object, a Visibility map, and a Player object in a
     * CampaignDetailsVisibilityRequest.
     * @param campaignDetailsVisibilityRequest CampaignDetailsVisibilityRequest
     *                                         containing Campaign object,
     *                                         Visibility map, and Player
     *                                         object
     * @return CampaignDetailsVisibilityResponse containing Visibility map
     */
    CampaignDetailsVisibilityResponse getCampaignDetailsVisibilityResponse(CampaignDetailsVisibilityRequest campaignDetailsVisibilityRequest);
}