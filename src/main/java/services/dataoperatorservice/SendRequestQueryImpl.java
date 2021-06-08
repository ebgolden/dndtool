package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class SendRequestQueryImpl implements SendRequestQuery {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public RequestQueryResponse getRequestQueryResponse(RequestQueryRequest requestQueryRequest) {
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = dataOperatorBusinessLogicConverter.getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromRequestQueryResponse(requestQueryRequest);
        QueryIdAndResponseJsonBo queryIdAndResponseJsonBo = dataOperatorBusinessLogic.getQueryIdAndResponseJsonBo(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        return dataOperatorBusinessLogicConverter.getRequestQueryResponseFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
    }
}