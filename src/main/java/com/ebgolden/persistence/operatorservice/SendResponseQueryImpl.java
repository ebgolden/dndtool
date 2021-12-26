package com.ebgolden.persistence.operatorservice;

import com.google.inject.Inject;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class SendResponseQueryImpl implements SendResponseQuery {
    @Inject
    private OperatorBusinessLogicConverter operatorBusinessLogicConverter;
    @Inject
    private OperatorBusinessLogic operatorBusinessLogic;

    public ResponseQueryResponse getResponseQueryResponse(ResponseQueryRequest responseQueryRequest) {
        QueryIdAndResponseJsonBo queryIdAndResponseJsonBo = operatorBusinessLogicConverter.getQueryIdAndResponseJsonBoFromResponseQueryRequest(responseQueryRequest);
        queryIdAndResponseJsonBo = operatorBusinessLogic.getQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
        return operatorBusinessLogicConverter.getResponseQueryResponseFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
    }
}