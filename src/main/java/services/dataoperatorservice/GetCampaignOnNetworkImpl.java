package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.CampaignBo;
import services.dataoperatorservice.bll.bo.PlayerBo;

public class GetCampaignOnNetworkImpl implements GetCampaignOnNetwork {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public CampaignOnNetworkResponse getCampaignOnNetworkResponse(CampaignOnNetworkRequest campaignOnNetworkRequest) {
        PlayerBo playerBo = dataOperatorBusinessLogicConverter.getPlayerBoFromCampaignOnNetworkRequest(campaignOnNetworkRequest);
        CampaignBo campaignBo = dataOperatorBusinessLogic.getCampaignBo(playerBo);
        return dataOperatorBusinessLogicConverter.getCampaignOnNetworkResponseFromCampaignBo(campaignBo);
    }
}