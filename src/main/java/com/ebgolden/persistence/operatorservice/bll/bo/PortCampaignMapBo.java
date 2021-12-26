package com.ebgolden.persistence.operatorservice.bll.bo;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class PortCampaignMapBo {
    Map<Integer, Campaign> portCampaignMap;
}