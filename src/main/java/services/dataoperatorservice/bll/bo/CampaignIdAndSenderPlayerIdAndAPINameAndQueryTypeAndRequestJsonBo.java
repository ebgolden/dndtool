package services.dataoperatorservice.bll.bo;

import commonobjects.QueryType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo {
    String campaignId;
    String senderPlayerId;
    String apiName;
    QueryType queryType;
    String requestJson;
}