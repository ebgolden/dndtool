package com.ebgolden.persistence.operatorservice.dal.dao;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class PortCampaignMapDao {
    Map<Integer, Campaign> portCampaignMap;
}