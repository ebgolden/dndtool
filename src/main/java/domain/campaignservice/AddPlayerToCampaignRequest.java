package domain.campaignservice;

import common.Campaign;
import common.DungeonMaster;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPlayerToCampaignRequest {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}