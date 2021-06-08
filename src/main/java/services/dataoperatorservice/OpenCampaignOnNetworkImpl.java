package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.DungeonMasterBo;
import services.dataoperatorservice.bll.bo.PortBo;

public class OpenCampaignOnNetworkImpl implements OpenCampaignOnNetwork {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public OpenCampaignOnNetworkResponse getOpenCampaignOnNetworkResponse(OpenCampaignOnNetworkRequest openCampaignOnNetworkRequest) {
        DungeonMasterBo dungeonMasterBo = dataOperatorBusinessLogicConverter.getDungeonMasterBoFromOpenCampaignOnNetworkRequest(openCampaignOnNetworkRequest);
        PortBo portBo = dataOperatorBusinessLogic.getPortBo(dungeonMasterBo);
        return dataOperatorBusinessLogicConverter.getOpenCampaignOnNetworkResponseFromPortBo(portBo);
    }
}