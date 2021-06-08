package services.dataoperatorservice;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CampaignListOnNetworkResponse {
    Map<Integer, Campaign> portCampaignMap;
}