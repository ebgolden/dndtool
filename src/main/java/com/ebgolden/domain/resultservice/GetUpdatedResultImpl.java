package domain.resultservice;

import com.google.inject.Inject;
import domain.resultservice.bll.ResultBusinessLogic;
import domain.resultservice.bll.ResultBusinessLogicConverter;
import domain.resultservice.bll.bo.ResultAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityBo;

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