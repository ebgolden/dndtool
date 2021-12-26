package com.ebgolden.persistence.operatorservice.bll.bo;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}