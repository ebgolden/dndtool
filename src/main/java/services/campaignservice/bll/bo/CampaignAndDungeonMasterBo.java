package services.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;

@Builder
@Value
public class CampaignAndDungeonMasterBo {
    Campaign campaign;
    DungeonMaster dungeonMaster;
}