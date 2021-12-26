package com.ebgolden.domain.campaignservice;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPlayerToCampaignResponse {
    Campaign campaign;
}