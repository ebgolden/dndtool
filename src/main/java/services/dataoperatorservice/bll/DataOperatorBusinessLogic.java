package services.dataoperatorservice.bll;

import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public interface DataOperatorBusinessLogic {
    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
}