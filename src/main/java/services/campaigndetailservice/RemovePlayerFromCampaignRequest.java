package services.campaigndetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Player;

@Builder
@Value
public class RemovePlayerFromCampaignRequest {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}