package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.IPAddressBo;
import services.dataoperatorservice.bll.bo.PlayerBo;

public class GetCampaignNetworkAddressImpl implements GetCampaignNetworkAddress {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public CampaignNetworkAddressResponse getCampaignNetworkAddressResponse(CampaignNetworkAddressRequest campaignNetworkAddressRequest) {
        PlayerBo playerBo = dataOperatorBusinessLogicConverter.getPlayerBoFromCampaignNetworkAddressRequest(campaignNetworkAddressRequest);
        IPAddressBo ipAddressBo = dataOperatorBusinessLogic.getIPAddressBo(playerBo);
        return dataOperatorBusinessLogicConverter.getCampaignNetworkAddressResponseFromIPAddressBo(ipAddressBo);
    }
}