package com.ebgolden.domain.campaignservice;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Visibility;
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