package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import com.ebgolden.persistence.operatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class SendRequestQueryImpl implements SendRequestQuery {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public RequestQueryResponse getRequestQueryResponse(RequestQueryRequest requestQueryRequest) {
        CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo = operatorBusinessLogicConverter.getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromRequestQueryResponse(requestQueryRequest);
        QueryIdAndResponseJsonBo queryIdAndResponseJsonBo = operatorBusinessLogic.getQueryIdAndResponseJsonBo(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);
        return operatorBusinessLogicConverter.getRequestQueryResponseFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
    }
}