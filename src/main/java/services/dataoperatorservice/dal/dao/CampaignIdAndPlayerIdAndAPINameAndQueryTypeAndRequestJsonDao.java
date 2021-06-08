package services.dataoperatorservice.dal.dao;

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