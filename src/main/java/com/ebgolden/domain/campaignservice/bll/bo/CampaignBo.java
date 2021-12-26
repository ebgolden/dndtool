package com.ebgolden.domain.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Campaign;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}