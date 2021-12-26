package domain.resultservice;

import com.google.inject.Inject;
import domain.resultservice.bll.ResultBusinessLogic;
import domain.resultservice.bll.ResultBusinessLogicConverter;
import domain.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityBo;

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