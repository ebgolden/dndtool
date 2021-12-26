package com.ebgolden.domain.resultservice.bll;

import com.ebgolden.domain.resultservice.ChangeVisibilityOfResultDetailsRequest;
import com.ebgolden.domain.resultservice.ChangeVisibilityOfResultDetailsResponse;
import com.ebgolden.domain.resultservice.UpdatedResultRequest;
import com.ebgolden.domain.resultservice.UpdatedResultResponse;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityBo;

public interface ResultBusinessLogicConverter {
    ResultAndPlayerBo getResultAndPlayerBoFromUpdatedResultRequest(UpdatedResultRequest updatedResultRequest);

    ResultAndVisibilityAndPlayerBo getResultAndVisibilityAndPlayerBoFromChangeVisibilityOfResultDetailsRequest(ChangeVisibilityOfResultDetailsRequest changeVisibilityOfUpdatedResultRequest);

    UpdatedResultResponse getUpdatedResultResponseFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo);

    ChangeVisibilityOfResultDetailsResponse getChangeVisibilityOfResultDetailsResponseFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo);

    ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityAndPlayerBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo);
}