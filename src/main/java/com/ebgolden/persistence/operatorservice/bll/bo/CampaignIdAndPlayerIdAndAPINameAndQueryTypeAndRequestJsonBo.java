package persistence.operatorservice.bll.bo;

import common.QueryType;
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