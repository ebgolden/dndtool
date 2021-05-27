package services.resultservice.bll;

import services.resultservice.bll.bo.ResultAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityBo;

public interface ResultBusinessLogic {
    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo);
}