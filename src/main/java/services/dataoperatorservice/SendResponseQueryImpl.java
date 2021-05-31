package services.dataoperatorservice;

import com.google.inject.Inject;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class SendResponseQueryImpl implements SendResponseQuery {
    @Inject
    private DataOperatorBusinessLogicConverter dataOperatorBusinessLogicConverter;
    @Inject
    private DataOperatorBusinessLogic dataOperatorBusinessLogic;

    public ResponseQueryResponse getResponseQueryResponse(ResponseQueryRequest responseQueryRequest) {
        QueryIdAndResponseJsonBo queryIdAndResponseJsonBo = dataOperatorBusinessLogicConverter.getQueryIdAndResponseJsonBoFromResponseQueryRequest(responseQueryRequest);
        queryIdAndResponseJsonBo = dataOperatorBusinessLogic.getQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
        return dataOperatorBusinessLogicConverter.getResponseQueryResponseFromQueryIdAndResponseJsonBo(queryIdAndResponseJsonBo);
    }
}