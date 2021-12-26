package domain.resultservice.dal;

import domain.resultservice.dal.dao.ResultDao;
import domain.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccess {
    ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao);

    ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);
}