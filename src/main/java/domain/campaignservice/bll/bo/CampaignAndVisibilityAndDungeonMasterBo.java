package domain.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Campaign;
import common.DungeonMaster;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class CampaignAndVisibilityAndDungeonMasterBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}