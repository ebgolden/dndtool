package domain.campaignservice;

import lombok.Builder;
import lombok.Value;
import common.Campaign;
import common.DungeonMaster;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCampaignRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}