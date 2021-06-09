package domain.resultservice.bll;

import domain.resultservice.bll.bo.ResultAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityBo;

public interface ResultBusinessLogic {
    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo);
}