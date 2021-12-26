package com.ebgolden.domain.campaignservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCampaignRequest {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}