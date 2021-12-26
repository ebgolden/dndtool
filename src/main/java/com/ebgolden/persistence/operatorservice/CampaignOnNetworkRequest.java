package com.ebgolden.persistence.operatorservice;

import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignOnNetworkRequest {
    Player player;
}