package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.PlayerBo;
import com.ebgolden.persistence.operatorservice.bll.bo.PortCampaignMapBo;

public class GetCampaignListOnNetworkImpl implements GetCampaignListOnNetwork {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public CampaignListOnNetworkResponse getCampaignListOnNetworkResponse(CampaignListOnNetworkRequest campaignListOnNetworkRequest) {
        PlayerBo playerBo = operatorBusinessLogicConverter.getPlayerBoFromCampaignListOnNetworkRequest(campaignListOnNetworkRequest);
        PortCampaignMapBo portCampaignMapBo = operatorBusinessLogic.getPortCampaignMapBo(playerBo);
        return operatorBusinessLogicConverter.getCampaignListOnNetworkResponseFromPortCampaignMapBo(portCampaignMapBo);
    }
}