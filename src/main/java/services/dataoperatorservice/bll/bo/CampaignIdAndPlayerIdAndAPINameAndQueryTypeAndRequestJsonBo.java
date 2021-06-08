package services.dataoperatorservice.bll.bo;

import commonobjects.QueryType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo {
    String campaignId;
    String playerId;
    String apiName;
    QueryType queryType;
    String requestJson;
}