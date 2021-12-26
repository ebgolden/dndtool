package com.ebgolden.persistence.operatorservice.dal.dao;

import com.ebgolden.common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignDao {
    Campaign campaign;
}