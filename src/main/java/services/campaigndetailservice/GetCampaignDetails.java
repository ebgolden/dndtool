package services.campaigndetailservice;

public interface GetCampaignDetails {
    /**
     * Returns a CampaignDetailsResponse containing a Campaign object.
     * Accepts a Campaign object and a Player object in a
     * CampaignDetailsRequest.
     * @param campaignDetailsRequest CampaignDetailsRequest containing
     *                               Campaign object and Player object
     * @return CampaignDetailsResponse containing Campaign object
     */
    CampaignDetailsResponse getCampaignDetailsResponse(CampaignDetailsRequest campaignDetailsRequest);
}