package services.dataoperatorservice.bll.bo;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;
import java.net.ServerSocket;
import java.util.Map;

@Builder
@Value
public class ServerSocketCampaignMapBo {
    Map<ServerSocket, Campaign> serverSocketCampaignMap;
}