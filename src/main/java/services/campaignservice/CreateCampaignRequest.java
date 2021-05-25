package services.campaignservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;

@Builder
@Value
public class CreateCampaignRequest {
    Campaign campaign;
    DungeonMaster dungeonMaster;
}