package services.dataoperatorservice;

public interface GetCampaignListOnNetwork {
    /**
     * Returns a CampaignListOnNetworkResponse containing a ServerSocket to Campaign map.
     * Accepts a Player object in a CampaignListOnNetworkRequest.
     * @param campaignListOnNetworkRequest CampaignListOnNetworkRequest containing Player
     *                                     object
     * @return CampaignListOnNetworkResponse containing ServerSocket to Campaign map
     */
    CampaignListOnNetworkResponse getCampaignListOnNetworkResponse(CampaignListOnNetworkRequest campaignListOnNetworkRequest);
}