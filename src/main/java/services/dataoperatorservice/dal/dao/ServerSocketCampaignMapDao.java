package services.dataoperatorservice.dal.dao;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;
import java.net.ServerSocket;
import java.util.Map;

@Builder
@Value
public class ServerSocketCampaignMapDao {
    Map<ServerSocket, Campaign> serverSocketCampaignMap;
}