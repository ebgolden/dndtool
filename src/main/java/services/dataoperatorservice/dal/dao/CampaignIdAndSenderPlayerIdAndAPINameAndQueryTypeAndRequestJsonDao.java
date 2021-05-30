package services.dataoperatorservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao {
    String campaignId;
    String senderPlayerId;
    String apiName;
    String queryType;
    String requestJson;
}