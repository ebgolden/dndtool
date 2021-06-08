package services.dataoperatorservice;

import commonobjects.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OpenCampaignOnNetworkRequest {
    DungeonMaster dungeonMaster;
}