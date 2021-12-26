package persistence.operatorservice;

import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignNetworkAddressRequest {
    Player player;
}