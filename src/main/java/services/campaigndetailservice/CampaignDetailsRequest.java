package services.campaigndetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.Player;

@Builder
@Value
public class CampaignDetailsRequest {
    Campaign campaign;
    Player player;
}