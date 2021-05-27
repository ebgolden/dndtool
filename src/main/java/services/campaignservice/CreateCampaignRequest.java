package services.campaignservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Campaign;
import commonobjects.DungeonMaster;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCampaignRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}