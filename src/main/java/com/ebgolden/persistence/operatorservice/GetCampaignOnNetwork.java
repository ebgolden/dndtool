package com.ebgolden.persistence.operatorservice;

public interface GetCampaignOnNetwork {
    /**
     * Returns a CampaignOnNetworkResponse containing a Campaign object.
     * Accepts a Player object in a CampaignOnNetworkRequest.
     * @param campaignOnNetworkRequest CampaignOnNetworkRequest
     *                                 containing Player object
     * @return CampaignOnNetworkResponse containing Campaign object
     */
    CampaignOnNetworkResponse getCampaignOnNetworkResponse(CampaignOnNetworkRequest campaignOnNetworkRequest);
}