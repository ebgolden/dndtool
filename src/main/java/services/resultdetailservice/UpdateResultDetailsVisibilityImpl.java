package services.resultdetailservice;

import com.google.inject.Inject;
import services.resultdetailservice.bll.ResultDetailBusinessLogic;
import services.resultdetailservice.bll.ResultDetailBusinessLogicConverter;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityBo;

public class UpdateResultDetailsVisibilityImpl implements UpdateResultDetailsVisibility {
    @Inject
    private ResultDetailBusinessLogicConverter resultDetailBusinessLogicConverter;
    @Inject
    private ResultDetailBusinessLogic resultDetailBusinessLogic;

    public ResultDetailsVisibilityResponse getResultDetailsVisibilityResponse(ResultDetailsVisibilityRequest resultDetailsVisibilityRequest) {
        ResultDetailsAndVisibilityAndPlayerBo resultDetailsAndVisibilityAndPlayerBo = resultDetailBusinessLogicConverter.getResultDetailsAndVisibilityAndPlayerBoFromResultDetailsVisibilityRequest(resultDetailsVisibilityRequest);
        ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo = resultDetailBusinessLogic.getResultDetailsAndVisibilityBo(resultDetailsAndVisibilityAndPlayerBo);
        return resultDetailBusinessLogicConverter.getResultDetailsVisibilityResponseFromResultDetailsAndVisibilityBo(resultDetailsAndVisibilityBo);
    }
}