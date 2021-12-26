package com.ebgolden.persistence.operatorservice;

import com.ebgolden.common.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OpenCampaignOnNetworkRequest {
    DungeonMaster dungeonMaster;
}