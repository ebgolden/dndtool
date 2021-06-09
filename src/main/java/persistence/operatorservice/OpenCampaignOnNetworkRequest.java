package persistence.operatorservice;

import common.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OpenCampaignOnNetworkRequest {
    DungeonMaster dungeonMaster;
}