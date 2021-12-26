package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.DungeonMasterBo;
import com.ebgolden.persistence.operatorservice.bll.bo.PortBo;

public class OpenCampaignOnNetworkImpl implements OpenCampaignOnNetwork {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public OpenCampaignOnNetworkResponse getOpenCampaignOnNetworkResponse(OpenCampaignOnNetworkRequest openCampaignOnNetworkRequest) {
        DungeonMasterBo dungeonMasterBo = operatorBusinessLogicConverter.getDungeonMasterBoFromOpenCampaignOnNetworkRequest(openCampaignOnNetworkRequest);
        PortBo portBo = operatorBusinessLogic.getPortBo(dungeonMasterBo);
        return operatorBusinessLogicConverter.getOpenCampaignOnNetworkResponseFromPortBo(portBo);
    }
}