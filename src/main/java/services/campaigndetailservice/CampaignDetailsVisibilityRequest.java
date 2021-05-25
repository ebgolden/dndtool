package services.campaigndetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.DungeonMaster;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CampaignDetailsVisibilityRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}