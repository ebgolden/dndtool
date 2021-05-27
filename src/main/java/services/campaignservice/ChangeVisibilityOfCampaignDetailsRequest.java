package services.campaignservice;

import commonobjects.Campaign;
import commonobjects.DungeonMaster;
import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCampaignDetailsRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}