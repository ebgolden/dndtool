package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class SendQueryImpl implements SendQuery {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public QueryResponse getQueryResponse(QueryRequest queryRequest) {
        CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = dataOperatorBusinessLogicConverter.getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromQueryRequest(queryRequest);
        QueryIdAndResponseJsonBo queryIdAndResponseJsonBo = dataOperatorBusinessLogic.getQueryIdAndResponseJsonBo(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        return dataOperatorBusinessLogicConverter.getQueryResponseFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
    }
}