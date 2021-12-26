package com.ebgolden.domain.campaignservice.bll.bo;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CampaignAndVisibilityBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
}