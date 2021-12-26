package domain.campaignservice;

public interface AddPlayerToCampaign {
    /**
     * Returns a AddPlayerToCampaignResponse containing a Campaign object.
     * Accepts a Campaign object, a Player object, and a DungeonMaster
     * object in a AddPlayerToCampaignRequest.
     * @param addPlayerToCampaignRequest AddPlayerToCampaignRequest
     *                                   containing Campaign object,
     *                                   Player object, and DungeonMaster
     *                                   object
     * @return AddPlayerToCampaignResponse containing Campaign object
     */
    AddPlayerToCampaignResponse getAddPlayerToCampaignResponse(AddPlayerToCampaignRequest addPlayerToCampaignRequest);
}