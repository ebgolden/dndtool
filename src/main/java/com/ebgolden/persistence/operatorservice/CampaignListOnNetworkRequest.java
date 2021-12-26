package com.ebgolden.persistence.operatorservice;

import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignListOnNetworkRequest {
    Player player;
}