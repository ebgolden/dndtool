package persistence.operatorservice;

import com.google.inject.Inject;
import persistence.operatorservice.bll.OperatorBusinessLogic;
import persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import persistence.operatorservice.bll.bo.DungeonMasterBo;
import persistence.operatorservice.bll.bo.PortBo;

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