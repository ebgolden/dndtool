package com.ebgolden.persistence.operatorservice;

public interface GetCampaignNetworkAddress {
    /**
     * Returns a CampaignNetworkAddressResponse containing a ipAddress.
     * Accepts a Player object in a CampaignNetworkAddressRequest.
     * @param campaignNetworkAddressRequest CampaignNetworkAddressRequest
     *                                      containing Player object
     * @return CampaignNetworkAddressResponse containing ipAddress
     */
    CampaignNetworkAddressResponse getCampaignNetworkAddressResponse(CampaignNetworkAddressRequest campaignNetworkAddressRequest);
}