package services.campaignservice.bll.bo;

import commonobjects.Campaign;
import commonobjects.DungeonMaster;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerAndDungeonMasterBo {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}