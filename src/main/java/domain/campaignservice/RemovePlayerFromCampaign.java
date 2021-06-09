package domain.campaignservice;

public interface RemovePlayerFromCampaign {
    /**
     * Returns a RemovePlayerFromCampaignResponse containing a Campaign object.
     * Accepts a Campaign object, a Player object, and a DungeonMaster object
     * in a RemovePlayerFromCampaignRequest.
     * @param removePlayerFromCampaignRequest RemovePlayerFromCampaignRequest
     *                                        containing Campaign object,
     *                                        Player object, and DungeonMaster
     *                                        object
     * @return RemovePlayerFromCampaignResponse containing Campaign object
     */
    RemovePlayerFromCampaignResponse getRemovePlayerFromCampaignResponse(RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest);
}