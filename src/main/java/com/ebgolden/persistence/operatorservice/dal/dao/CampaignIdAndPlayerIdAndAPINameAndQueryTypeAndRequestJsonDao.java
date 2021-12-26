package com.ebgolden.persistence.operatorservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao {
    String campaignId;
    String playerId;
    String apiName;
    String queryType;
    String requestJson;
}