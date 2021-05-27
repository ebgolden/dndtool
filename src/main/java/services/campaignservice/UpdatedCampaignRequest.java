package services.campaignservice;

import commonobjects.Campaign;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCampaignRequest {
    Campaign campaign;
    Player player;
}