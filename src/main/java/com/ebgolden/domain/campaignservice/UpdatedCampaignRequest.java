package domain.campaignservice;

import common.Campaign;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCampaignRequest {
    Campaign campaign;
    Player player;
}