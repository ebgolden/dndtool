package services.resultservice;

import com.google.inject.Inject;
import services.resultservice.bll.ResultBusinessLogic;
import services.resultservice.bll.ResultBusinessLogicConverter;
import services.resultservice.bll.bo.ResultAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityBo;

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