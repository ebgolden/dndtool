package domain.campaignservice.bll.bo;

import common.Campaign;
import common.DungeonMaster;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerAndDungeonMasterBo {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}