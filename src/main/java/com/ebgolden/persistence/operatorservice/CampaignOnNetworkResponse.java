package com.ebgolden.persistence.operatorservice;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignOnNetworkResponse {
    Campaign campaign;
}