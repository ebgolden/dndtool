package com.ebgolden.domain.resultservice;

import com.google.inject.Inject;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogic;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogicConverter;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityBo;

public class GetUpdatedResultImpl implements GetUpdatedResult {
    @Inject
    private ResultBusinessLogicConverter resultBusinessLogicConverter;
    @Inject
    private ResultBusinessLogic resultBusinessLogic;

    public UpdatedResultResponse getUpdatedResultResponse(UpdatedResultRequest updatedResultRequest) {
        ResultAndPlayerBo resultAndPlayerBo = resultBusinessLogicConverter.getResultAndPlayerBoFromUpdatedResultRequest(updatedResultRequest);
        ResultAndVisibilityBo resultAndVisibilityBo = resultBusinessLogic.getResultAndVisibilityBo(resultAndPlayerBo);
        return resultBusinessLogicConverter.getUpdatedResultResponseFromResultAndVisibilityBo(resultAndVisibilityBo);
    }
}