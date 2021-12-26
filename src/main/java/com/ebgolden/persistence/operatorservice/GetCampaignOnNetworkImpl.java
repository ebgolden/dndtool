package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.CampaignBo;
import com.ebgolden.persistence.operatorservice.bll.bo.PlayerBo;

public class GetCampaignOnNetworkImpl implements GetCampaignOnNetwork {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public CampaignOnNetworkResponse getCampaignOnNetworkResponse(CampaignOnNetworkRequest campaignOnNetworkRequest) {
        PlayerBo playerBo = operatorBusinessLogicConverter.getPlayerBoFromCampaignOnNetworkRequest(campaignOnNetworkRequest);
        CampaignBo campaignBo = operatorBusinessLogic.getCampaignBo(playerBo);
        return operatorBusinessLogicConverter.getCampaignOnNetworkResponseFromCampaignBo(campaignBo);
    }
}