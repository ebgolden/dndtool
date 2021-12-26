package domain.resultservice.dal;

import domain.resultservice.bll.bo.ResultAndPlayerBo;
import domain.resultservice.bll.bo.ResultAndVisibilityBo;
import domain.resultservice.dal.dao.ResultDao;
import domain.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccessConverter {
    ResultDao getResultDaoFromResultAndPlayerBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo);

    ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityJson(String resultAndVisibilityJson);
}