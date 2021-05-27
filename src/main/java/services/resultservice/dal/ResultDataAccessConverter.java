package services.resultservice.dal;

import services.resultservice.bll.bo.ResultAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityBo;
import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccessConverter {
    ResultDao getResultDaoFromResultAndPlayerBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo);

    ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityJson(String resultAndVisibilityJson);
}