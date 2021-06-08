package services.dataoperatorservice;

public interface OpenCampaignOnNetwork {
    /**
     * Returns a OpenCampaignOnNetworkResponse containing a port.
     * Accepts a DungeonMaster object in a
     * OpenCampaignOnNetworkRequest.
     * @param openCampaignOnNetworkRequest OpenCampaignOnNetworkRequest
     *                                     containing DungeonMaster
     *                                     object
     * @return OpenCampaignOnNetworkResponse containing port
     */
    OpenCampaignOnNetworkResponse getOpenCampaignOnNetworkResponse(OpenCampaignOnNetworkRequest openCampaignOnNetworkRequest);
}