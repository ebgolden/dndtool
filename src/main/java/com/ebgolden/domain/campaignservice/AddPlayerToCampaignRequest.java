package com.ebgolden.domain.campaignservice;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPlayerToCampaignRequest {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}