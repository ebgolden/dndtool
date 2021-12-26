package com.ebgolden.domain.campaignservice.bll.bo;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerAndDungeonMasterBo {
    Campaign campaign;
    Player player;
    DungeonMaster dungeonMaster;
}