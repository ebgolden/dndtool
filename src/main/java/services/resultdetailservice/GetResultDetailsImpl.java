package services.resultdetailservice;

import com.google.inject.Inject;
import services.resultdetailservice.bll.ResultDetailBusinessLogic;
import services.resultdetailservice.bll.ResultDetailBusinessLogicConverter;
import services.resultdetailservice.bll.bo.ResultAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityBo;

public class GetResultDetailsImpl implements GetResultDetails {
    @Inject
    private ResultDetailBusinessLogicConverter resultDetailBusinessLogicConverter;
    @Inject
    private ResultDetailBusinessLogic resultDetailBusinessLogic;

    public ResultDetailsResponse getResultDetailsResponse(ResultDetailsRequest resultDetailsRequest) {
        ResultAndPlayerBo resultAndPlayerBo = resultDetailBusinessLogicConverter.getResultAndPlayerBoFromResultDetailsRequest(resultDetailsRequest);
        ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo = resultDetailBusinessLogic.getResultDetailsAndVisibilityBo(resultAndPlayerBo);
        return resultDetailBusinessLogicConverter.getResultDetailsResponseFromResultDetailsAndVisibilityBo(resultDetailsAndVisibilityBo);
    }
}