package com.ebgolden.domain.campaignservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Campaign;

@Builder
@Value
public class CreateCampaignResponse {
    Campaign campaign;
}