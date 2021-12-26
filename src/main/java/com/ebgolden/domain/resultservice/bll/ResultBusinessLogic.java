package com.ebgolden.domain.resultservice.bll;

import com.ebgolden.domain.resultservice.bll.bo.ResultAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityBo;

public interface ResultBusinessLogic {
    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo);
}