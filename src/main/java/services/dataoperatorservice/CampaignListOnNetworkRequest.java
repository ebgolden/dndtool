package services.dataoperatorservice;

import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignListOnNetworkRequest {
    Player player;
}