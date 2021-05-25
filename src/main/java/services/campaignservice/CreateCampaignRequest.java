package services.campaignservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCampaignRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}