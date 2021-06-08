package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.PlayerBo;
import services.dataoperatorservice.bll.bo.PortCampaignMapBo;

public class GetCampaignListOnNetworkImpl implements GetCampaignListOnNetwork {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public CampaignListOnNetworkResponse getCampaignListOnNetworkResponse(CampaignListOnNetworkRequest campaignListOnNetworkRequest) {
        PlayerBo playerBo = dataOperatorBusinessLogicConverter.getPlayerBoFromCampaignListOnNetworkRequest(campaignListOnNetworkRequest);
        PortCampaignMapBo portCampaignMapBo = dataOperatorBusinessLogic.getPortCampaignMapBo(playerBo);
        return dataOperatorBusinessLogicConverter.getCampaignListOnNetworkResponseFromPortCampaignMapBo(portCampaignMapBo);
    }
}