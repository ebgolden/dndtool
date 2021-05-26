package services.campaigndetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Player;

@Builder
@Value
public class CampaignAndPlayerAndDungeonMasterBo {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}