package services.resultservice;

import com.google.inject.Inject;
import services.resultservice.bll.ResultBusinessLogic;
import services.resultservice.bll.ResultBusinessLogicConverter;
import services.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityBo;

public class ChangeVisibilityOfResultDetailsImpl implements ChangeVisibilityOfResultDetails {
    @Inject
    private ResultBusinessLogicConverter resultBusinessLogicConverter;
    @Inject
    private ResultBusinessLogic resultBusinessLogic;

    public ChangeVisibilityOfResultDetailsResponse getChangeVisibilityOfResultDetailsResponse(ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest) {
        ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo = resultBusinessLogicConverter.getResultAndVisibilityAndPlayerBoFromChangeVisibilityOfResultDetailsRequest(changeVisibilityOfUpdatedResultRequest);
        ResultAndVisibilityBo resultAndVisibilityBo = resultBusinessLogic.getResultAndVisibilityBo(resultAndVisibilityAndPlayerBo);
        return resultBusinessLogicConverter.getChangeVisibilityOfResultDetailsResponseFromResultAndVisibilityBo(resultAndVisibilityBo);
    }
}