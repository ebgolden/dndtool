package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.bo.PlayerBo;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.IPAddressBo;

public class GetCampaignNetworkAddressImpl implements GetCampaignNetworkAddress {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public CampaignNetworkAddressResponse getCampaignNetworkAddressResponse(CampaignNetworkAddressRequest campaignNetworkAddressRequest) {
        PlayerBo playerBo = operatorBusinessLogicConverter.getPlayerBoFromCampaignNetworkAddressRequest(campaignNetworkAddressRequest);
        IPAddressBo ipAddressBo = operatorBusinessLogic.getIPAddressBo(playerBo);
        return operatorBusinessLogicConverter.getCampaignNetworkAddressResponseFromIPAddressBo(ipAddressBo);
    }
}