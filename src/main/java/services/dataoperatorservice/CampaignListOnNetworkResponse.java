package services.dataoperatorservice;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;
import java.net.ServerSocket;
import java.util.Map;

@Builder
@Value
public class CampaignListOnNetworkResponse {
    Map<ServerSocket, Campaign> serverSocketCampaignMap;
}