package services.campaignservice;

import commonobjects.Campaign;
import commonobjects.DungeonMaster;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RemovePlayerFromCampaignRequest {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}